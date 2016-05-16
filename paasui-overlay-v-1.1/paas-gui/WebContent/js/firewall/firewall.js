var myfirewall = angular.module('myfirewall', []);

myfirewall.controller('MainCtrl', function($scope,$http) {
	$scope.field = {};
	$scope.field1 = {};
	$scope.showModal = false;
	$scope.toggleModal = function() {
		$scope.showModal = !$scope.showModal;
	};
	
	$scope.showModal1 = false;
	$scope.toggleModal1 = function() {
		$scope.showModal1 = !$scope.showModal1;
	};
	
	
/*============ FIREWALL OUTBOUNDS REG=============*/
    
    $scope.regFirewallOutbounds = function() {
    	console.log($scope.field);
    	
    	var userData = JSON.stringify($scope.field);
    	var res = $http.post('/PAAS-GUI/rest/firewallService/addFirewallOutbound', userData);
    	console.log("storeFirewallOutbounds :"+res);
    	console.log(userData);
    	

    	res.success(function(data, status, headers, config) {
    		$scope.message = data;
    		//document.location.href = '/PAAS-GUI/html/login.html';
    	});
    	res.error(function(data, status, headers, config) {
    		alert("failure message: " + JSON.stringify({
    			data : data
    		}));
    	});
    }; 
    
    
    /*==================POPULATE DATA TO TABLE===================*/
    
 	 $scope.selectFirewallOutbounds = function() {
 		
    	var response = $http.get('/PAAS-GUI/rest/firewallService/getAllFirewallOutbound');
    	response.success(function(data){
    		console.log(" inside selectFirewallOutbounds");
    		$scope.fields = data;
    		console.log("selectFirewallOutbounds : "+$scope.fields);
    	});
    	response.error(function(data, status, headers, config) {
                alert("Error in Fetching Data");
        });
    };           

    /*=================== delete*====================*/
   
    $scope.deleteFirewallOutbounds = function(data) {
     	var response = $http.get('/PAAS-GUI/rest/firewallService/deleteOutboundFirewallByName/'+data);
     	response.success(function(data){
     		$scope.select();
     	});
     	response.error(function(data, status, headers, config) {
                 alert("Error in Fetching Data");
         });
     	
     };
    
    
    
    
    
    /*================end of controllers===================*/
	
	
	
/*============ FIREWALL INBOUNDS REG=============*/

$scope.regFirewallInbounds = function() {
	//alert("store Inbound");
	
	var userData = JSON.stringify($scope.field1);
	var res = $http.post('/PAAS-GUI/rest/firewallService/addFirewallInbound', userData);
	console.log(userData);

	res.success(function(data, status, headers, config) {
		$scope.message = data;
		//document.location.href = '/PAAS-GUI/html/login.html';
	});
	res.error(function(data, status, headers, config) {
		alert("failure message: " + JSON.stringify({
			data : data
		}));
	});
}; 


/*==================POPULATE DATA TO TABLE===================*/

	 $scope.selectFirewallInbounds = function() {
		
	var response = $http.get('/PAAS-GUI/rest/firewallService/getAllInboundFirewall');
	response.success(function(data){
		console.log(" inside selectFirewallInbounds");
		$scope.fields1 = data;
		
		console.log("selectFirewallInbounds : "+$scope.fields1);
	});
	response.error(function(data, status, headers, config) {
            alert("Error in Fetching Data");
    });
};           

/*=================== delete*====================*/

$scope.deleteFirewallInbounds = function(data) {
 	var response = $http.get('/PAAS-GUI/rest/firewallService/deleteInboundFirewallByName/'+data);
 	response.success(function(data){
 		$scope.select();
 	});
 	response.error(function(data, status, headers, config) {
             alert("Error in Fetching Data");
     });
 	

};















});   /*================end of controllers===================*/
	



myfirewall
		.directive(
				'modal',
				function() {
					return {
						template : '<div class="modal fade">'
								+ '<div class="modal-dialog">'
								+ '<div class="modal-content">'
								+ '<div class="modal-header">'
								+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
								+ '<h4 class="modal-title">{{ title }}</h4>'
								+ '</div>'
								+ '<div class="modal-body" ng-transclude></div>'
								+ '</div>' + '</div>' + '</div>',
						restrict : 'E',
						transclude : true,
						replace : true,
						scope : true,
						link : function postLink(scope, element, attrs) {
							scope.title = attrs.title;

							scope.$watch(attrs.visible, function(value) {
								if (value == true)
									$(element).modal('show');
								else
									$(element).modal('hide');
							});

							$(element).on('shown.bs.modal', function() {
								scope.$apply(function() {
									scope.$parent[attrs.visible] = true;
								});
							});

							$(element).on('hidden.bs.modal', function() {
								scope.$apply(function() {
									scope.$parent[attrs.visible] = false;
								});
							});
						}
					};
				});



myfirewall
		.directive(
				'modal1',
				function() {
					return {
						template : '<div class="modal1 fade">'
								+ '<div class="modal1-dialog">'
								+ '<div class="modal1-content">'
								+ '<div class="modal1-header">'
								+ '<button type="button" class="close" data-dismiss="modal1" aria-hidden="true">&times;</button>'
								+ '<h4 class="modal1-title">{{ title }}</h4>'
								+ '</div>'
								+ '<div class="modal1-body" ng-transclude></div>'
								+ '</div>' + '</div>' + '</div>',
						restrict : 'E',
						transclude : true,
						replace : true,
						scope : true,
						link : function postLink(scope, element, attrs) {
							scope.title = attrs.title;

							scope.$watch(attrs.visible, function(value) {
								if (value == true)
									$(element).modal1('show');
								else
									$(element).modal1('hide');
							});

							$(element).on('shown.bs.modal1', function() {
								scope.$apply(function() {
									scope.$parent[attrs.visible] = true;
								});
							});

							$(element).on('hidden.bs.modal1', function() {
								scope.$apply(function() {
									scope.$parent[attrs.visible] = false;
								});
							});
						}
					};
				});