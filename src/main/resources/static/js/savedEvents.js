let objectTr = {
    idEvent: '',
    startsAt: '',
    instance: '',
    alertName: '',
    annotationSummary: '',
    severity: ''
};
$(document).ready(function () {
    $.ajax({
        url: "/collector/api/getAllEvent/",
        type: "Get",
        async: false,
        contentType: "application/json",
        data: {},
        success: function (data) {
            outputEvent(data);

        }
    });

});

function createTrTbody(event) {

    var trTable = '<tr id=' + event.idEvent + '> <td>' + event.startsAt + '</td><td >' + event.instance + '</td><td>'
        + event.alertName + '</td><td>' + event.annotationSummary + '</td><td>' + event.severity + '</td></tr>';
    $('#dataTable').append(trTable);
}

function outputEvent(data) {
    for (let i in data) {

        objectTr.idEvent = data[i].idEvent;
        objectTr.startsAt = data[i].startsAt;
        objectTr.annotationSummary = data[i].annotations.summary;
        for (let j in data[i].labelList) {
            if (data[i].labelList[j].labelName == "severity") {
                objectTr.severity = data[i].labelList[j].labelData;
            }
            if (data[i].labelList[j].labelName == "alertname") {
                objectTr.alertName = data[i].labelList[j].labelData;
            }
            if (data[i].labelList[j].labelName == "instance") {
                objectTr.instance = data[i].labelList[j].labelData;
            }

        }
        createTrTbody(objectTr);
        for (var key in objectTr) {
           delete objectTr[key];
        }
    }
}
