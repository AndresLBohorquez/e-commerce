var options = {
  series: [{
  name: 'Ventas',
  data: [31, 40, 28, 51, 42, 109, 100],
}],

  chart: {
  height: 350,
  type: 'area',
  width: '100%'
},
dataLabels: {
  enabled: false

},
stroke: {
  curve: 'smooth'
},
xaxis: {
  type: 'datetime',
  categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
},
tooltip: {
  x: {
    format: 'dd/MM/yy HH:mm'
  },
},
colors:['#0A9A21']

};


var chart = new ApexCharts(document.querySelector("#chart"), options);
chart.render();