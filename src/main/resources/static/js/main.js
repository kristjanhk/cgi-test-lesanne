/**
 * Created by Kristjan on 17.04.2017.
 */
//todo kasutatud sõned messages.properties faili
$(document).ready(function () {
    $(".datepicker").pickadate({ //kalender
        selectMonths: true,
        selectYears: 10,
        firstDay: 1,
        format: 'dd.mm.yyyy',
        formatSubmit: 'dd.mm.yyyy'
    });
    $("#search").keyup(function (e) { //otsimise enter nupp
        if (e.keyCode === 13) {
            $("#search-button").click();
        }
    });
    var modalCounter = 0;
    $('#search-button').click(function () { //otsimine
        $("#search-result").empty();
        $("#modals").empty();
        var queryString = $('#search').val();
        $.ajax({
            type: 'GET',
            contentType: "application/json",
            url: "/registrations/query/" + queryString,
            cache: false,
            success: function (response) {
                console.log(response);
                var visits = response['visits'];
                if (visits.length > 0) {
                    $.each(visits, function (i) {
                        var data = visits[i];
                        var modalId = 'modal-' + modalCounter++;
                        var date = new Date(data['visitDateTime']);
                        var arrayOfNodes = $.parseHTML( //visiidi nupp
                            '<li class="collection-item">' +
                            '<div class="row">' +
                            '<a class="btn col s12 red waves-effect waves-light" href="#' + modalId + '">' +
                            '<span class="title">' + data['dentistName'] + ' - ' + date.toDateString() +
                            '</span>' +
                            '</a>' +
                            '</div>' +
                            '</li>'
                        );
                        $('#modals').append( //visiidi detailne vaade
                            '<div id="' + modalId + '" class="modal">' +
                            '<div class="modal-content">' +

                            '<h4>Hambaarst: ' + data['dentistName'] + '</h4>' +
                            '<p>Hambaarst: ' + data['dentistName'] + '</p>' +
                            '<p>Perearst: ' + data['familyPhysicianName'] + '</p>' +
                            '<p>Kuupäev: ' + date.toDateString() + '</p>' +
                            '<p>Kellaaeg: ' + date.toTimeString() + '</p>' +

                            '</div>' +
                            '<div class="modal-footer">' +
                            '<a id=delete-"' + modalId + '" href="#" ' +
                            'class="modal-action modal-close waves-effect waves-green btn-flat">Kustuta</a>' +
                            '</div>' +
                            '</div>'
                        );
                        $('#' + modalId).bind('click', function () { //visiidi kustutamine
                            $("#search-result").empty();
                            $("#modals").empty();
                            $.ajax({
                                type: 'POST',
                                url: "/registrations/delete/" + data['id'],
                                success: function (data) {
                                    Materialize.toast('Visiit kustutatud!', 3000);
                                },
                                error: function (e) {
                                    console.log("Error: " + e);
                                }
                            });
                        });
                        arrayOfNodes[0].onclick = function () {
                            $('#' + modalId).modal('open');
                        };
                        arrayOfNodes[0].addEventListener("keyup", function (e) {
                            if (e.keyCode === 13) {
                                $('#' + modalId).modal('open');
                            }
                        });

                        $("#search-result").append(arrayOfNodes).show();
                    });
                    $('.modal').modal();
                } else {
                    $("#search-result").append( //kui antud otsinguga visiite ei leitud
                        $.parseHTML(
                            '<li class="collection-item">' +
                            '<div class="row">' +
                            '<h5>' + 'Registreerimisi ei leitud' + '</h5>' +
                            '</div>' +
                            '</li>'
                        )
                    ).show();
                }
            },
            error: function (e) {
                console.log("Error: " + e);
            }
        })
    });
});
