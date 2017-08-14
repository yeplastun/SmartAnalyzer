//chart.js
angular
.module('app')
.controller('LineCtrl', LineCtrl)
.controller('BarCtrl', BarCtrl)
.controller('DoughnutCtrl', DoughnutCtrl)
.controller('RadarCtrl', RadarCtrl)
.controller('PieCtrl', PieCtrl)
.controller('PolarAreaCtrl', PolarAreaCtrl)

LineCtrl.$inject = ['$scope'];
function LineCtrl($scope) {
  
	
	
	
	
	
	
	
    // set the dimensions and margins of the graph
    var margin = {top: 20, right: 20, bottom: 30, left: 40},
        width = 960 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;

    // set the ranges
    var x = d3.scaleBand()
        .range([0, width])
        .padding(0.1);
    var y = d3.scaleLinear()
        .range([height, 0]);

    // append the svg object to the body of the page
    // append a 'group' element to 'svg'
    // moves the 'group' element to the top left margin
    var svg = d3.select("#d3bar").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");

    // get the data
    d3.json("/visualization/avg_amounts_per_instrument_name", function (error, rawData) {
        if (error) throw error;

        // format the data
        var data = {
            names: Object.keys(rawData),
            values: Object.values(rawData)
        };

        var listedData = [];
        for (var property in rawData) {
            listedData.push({name: property, value: rawData[property]})
        }

        // Scale the range of the data in the domains
        x.domain(data.names);
        y.domain([0, d3.max(data.values)]);

//        listedData.columns = ["names", "values"]

        // append the rectangles for the bar chart
        svg.selectAll(".bar")
            .data(listedData)
            .enter().append("rect")
            .attr("class", "bar")
            .attr("x", function (d) {
                return x(d.name);
            })
            .attr("width", x.bandwidth())
            .attr("y", function (d) {
                return y(d.value);
            })
            .attr("height", function (d) {
                return height - y(d.value);
            });

        // add the x Axis
        svg.append("g")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x));

        // add the y Axis
        svg.append("g")
            .call(d3.axisLeft(y));

    });
	
	
	
	
	
	
	
	
}

BarCtrl.$inject = ['$scope'];
function BarCtrl($scope) {
  $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  $scope.series = ['Series A', 'Series B'];

  $scope.data = [
    [65, 59, 80, 81, 56, 55, 40],
    [28, 48, 40, 19, 86, 27, 90]
  ];
}

DoughnutCtrl.$inject = ['$scope'];
function DoughnutCtrl($scope) {
  $scope.labels = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
  $scope.data = [300, 500, 100];
}

RadarCtrl.$inject = ['$scope'];
function RadarCtrl($scope) {
  $scope.labels =['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'];

  $scope.data = [
    [65, 59, 90, 81, 56, 55, 40],
    [28, 48, 40, 19, 96, 27, 100]
  ];
}

PieCtrl.$inject = ['$scope'];
function PieCtrl($scope) {
  $scope.labels = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
  $scope.data = [300, 500, 100];
}

PolarAreaCtrl.$inject = ['$scope'];
function PolarAreaCtrl($scope) {
  $scope.labels = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales', 'Tele Sales', 'Corporate Sales'];
  $scope.data = [300, 500, 100, 40, 120];
}
