let objectTr = {
    startsAt: '',
    instance: '',
    alertName: '',
    annotationSummary: '',
    severity: ''
};
var table;
var dataRealTime = [];
var countAlertType=[0,0,0,0];
var timer = setInterval(function () {
        $.ajax({
            url: "api/getEventRealTime/",
            type: "Get",
            async: false,
            contentType: "application/json",
            data: {},
            success: function (data) {
                table.clear().draw();
                outputEvent(data);
                if (dataRealTime.length > 0)
                    dataRealTime = dataRealTime.slice(1);
                dataRealTime.push(data.length);
                countAlertType=[0,0,0,0];
                uptade();

            }
        })
    }, 5000
);

$(document).ready(function () {

    table = $('#dataTable').DataTable({
        "aoColumnDefs": [
            {"aTargets": [1], sDefaultContent: "n/a"},
        ],
        "createdRow": function (row, data, index) {
            if (data[1] == "") {
                data.set("_");
            }
            if (data[4] == "critical") {
                countAlertType[1]++;
                $('td', row).css("background-color", "#dc3545");
            }
            if (data[4] == "major" || data[4] == "Major") {
                countAlertType[0]++;
                $('td', row).css("background-color", "#007bff");
            }
            if (data[4] == "warning" ) {
                countAlertType[2]++;
                $('td', row).css("background-color", "#ffc107");
            }
            if (data[4] == "normal") {
                countAlertType[3]++;
                $('td', row).css("background-color", "#28a745");
            }
        }
    });

});


var container = $("#placeholder");
var series = [{
    data: getData(),
    lines: {
        fill: true
    }
}];

function getData() {
    var res = [];
    if (dataRealTime.length == 0) {
        while (dataRealTime.length < 50) {
            dataRealTime.push(0)
        }
    }
    for (var i = 0; i < dataRealTime.length; i++) {
        res.push([i, dataRealTime[i]])
    }
    return res;
}
var ctx = document.getElementById("myPieChart");
var config = {
    type: 'pie',
    data: {
        labels: ["major", "critical", "warning", "normal"],
        datasets: [{
            data: countAlertType,
            backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745'],
        }],
    },
    options: {
        responsive: true
    }
};
var myPieChart = new Chart(ctx,config);

var plot = $.plot(container, series, {
    grid: {
        borderWidth: 1,
        minBorderMargin: 20,
        labelMargin: 10,
        backgroundColor: {
            colors: ["#fff", "#e4f4f4"]
        },
        margin: {
            top: 8,
            bottom: 20,
            left: 20
        },
        markings: function (axes) {
            var markings = [];
            var xaxis = axes.xaxis;
            for (var x = Math.floor(xaxis.min); x < xaxis.max; x += xaxis.tickSize * 2) {
                markings.push({
                    xaxis: {
                        from: x,
                        to: x + xaxis.tickSize
                    },
                    color: "rgba(232, 232, 255, 0.2)"
                });
            }
            return markings;
        }
    },
    xaxis: {
        tickFormatter: function () {
            return "";
        }
    },
    yaxis: {
        min: 0,
        max: 90
    },
    legend: {
        show: true
    }
});
function uptade() {
    series[0].data = getData();
    plot.setData(series);
    console.log("++++");
    plot.draw();
    myPieChart.update();
}


//

// Update the random dataset at 25FPS for a smoothly-animating chart


function createTrTbody(event) {
    table.row.add([
        event.startsAt,
        event.instance,
        event.alertName,
        event.annotationSummary,
        event.severity]
    ).draw();
}

function outputEvent(data) {
    for (let i in data) {
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
