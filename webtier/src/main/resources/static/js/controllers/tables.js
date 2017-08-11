angular
.module('app')
.directive('input', function() {
        return {
            restrict: 'E',
            require: '?ngModel',
            link: function(scope, element, attrs, ngModel) {
                if('numeric' in attrs) { ngModel.$parsers.push(parseFloat); }
            }
        };
})
.filter('rangeFilter', function () {
    return function (items, attr, min, max) {
        var range = [],
            min=parseFloat(min),
            max=parseFloat(max);
        for (var i=0, l=items.length; i<l; ++i){
            var item = items[i];
            if(item[attr]<=max && item[attr]>=min){
                range.push(item);
            }
        }
        return range;
    };
})
.filter('startFrom', function() {
    return function(input, start) {
        if (!input || !input.length) { return; }
        start = +start; //parse to int
        return input.slice(start);
    }
})
.controller('instrumentsCtrl', instrumentsCtrl)
.controller('counterpartiesCtrl', counterpartiesCtrl)
.controller('dealsCtrl', dealsCtrl)


instrumentsCtrl.$inject = ['$scope','$http','$filter'];
function instrumentsCtrl($scope, $http, $filter) {
	
	
	$scope.sortType     = 'instrumentId'; // set the default sort type
	$scope.sortReverse  = false;  // set the default sort order
	
	
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = [];
    
    $scope.instruments = []
	$scope.instrumentform = {
			instrumentId : "",
			instrumentName : ""
		};

	getInstrumentDetails();

	function getInstrumentDetails() {
		$http({
			method : 'GET',
			url : 'instrument/all'
		}).then(function successCallback(response) {
			$scope.instruments = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	} 
    
    
    $scope.getData = function () {
    	return $filter('filter')($scope.instruments, $scope.q)
    }
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    } 
}

counterpartiesCtrl.$inject = ['$scope','$http','$filter'];
function counterpartiesCtrl($scope, $http, $filter) {

	$scope.sortType     = 'counterpartyId'; // set the default sort type
	$scope.sortReverse  = false;  // set the default sort order	
	
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = [];
	
	$scope.counterparties = [];
	$scope.counterpartyform = {
			counterpartyId : "",
			counterpartyName : "",
			counterpartyStatus : "",
			counterpartyDateRegistered : ""
		};
	
	getCounterpartyDetails();
	
	function getCounterpartyDetails() {
		$http({
			method : 'GET',
			url : 'counterparty/all'
		}).then(function successCallback(response) {
			$scope.counterparties = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}
	
    $scope.getData = function () {
    	return $filter('filter')($scope.counterparties, $scope.q)
    }
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
    
}


dealsCtrl.$inject = ['$scope','$http', '$filter'];
function dealsCtrl($scope, $http, $filter) {
	
	$scope.ui = {
        min: 0,
        max: 100000
    };  
	
	
	$scope.sortType     = 'dealId'; // set the default sort type
	$scope.sortReverse  = false;  // set the default sort order
	
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = [];
	
	$scope.deals = [];
	$scope.dealform = {
			dealId : "",
			dealTime : "",
			dealCounterpartyId : "",
			dealInstrumentId : "",
			dealType : "",
			dealAmount : "",
			dealQuantity : ""
		};
	
	getDealDetails();
	
	function getDealDetails() {
		$http({
			method : 'GET',
			url : 'deal/all'
		}).then(function successCallback(response) {
			$scope.deals = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}
	
	$scope.getData = function () {
    	return $filter('filter')($scope.deals, $scope.q)
    }
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
	
}