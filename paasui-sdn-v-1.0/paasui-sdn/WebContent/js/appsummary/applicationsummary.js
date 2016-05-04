var myvpc = angular.module('SummaryApp', ['ui.bootstrap']);

myvpc.controller('SummaryCtrl', function ($scope,$http) {
	$scope.scaling={};
	$scope.field = {};
	$scope.scale = {scales:[],env:[],route:[]};
	$scope.services={};
	$scope.scales = [{portname:'',porttype:'',hostport:'',containerport:''}];
	$scope.env = [{envkey:'',envvalue:''}];
	$scope.route= [{type:'',port:'',routetype:'',target:''}];
	
	
    $scope.showModal = false;
    $scope.toggleModal = function(){
        $scope.showModal = !$scope.showModal;
    };
    
    
    $scope.showModal1 = false;
    $scope.toggleModal1 = function(){
        $scope.showModal1 = !$scope.showModal1;
    };
    
    
      /*================VPC REGISTRATION===================*/
    
    $scope.storeSummary = function() {
  	  console.log($scope.field);
  
  	   userData = angular.toJson($scope.field);
  	   alert(userData);
  	  var res = $http.post('/PAAS-GUI/rest/applicationService/addApplicantSummary', userData);
  	  console.log(userData);
  	  res.success(function(data, status, headers, config) {
  	    $scope.message = data;
  	    
  	    
  	  });
  	  res.error(function(data, status, headers, config) {
  	    alert("failure message: " + JSON.stringify({
  	      data : data
  	    }));
  	  });
  	 
  	};
  	
  	//marathons  call, it is not called so commented it
  	 /*$scope.callMarathonsLaunch = function() {scale
  	  	  console.log($scope.field);
  	  
  	  	   userData = angular.toJson($scope.field);
  	  	   alert(userData);
  	  	  var res = $http.post('/PAAS-GUI/rest/fetchData/callMarathonsLaunch');
  	  	  
  	  	  res.success(function(data, status, headers, config) {
  	  	    $scope.message = data;
  	  	    
  	  	    
  	  	  });
  	  	  res.error(function(data, status, headers, config) {
  	  	    alert("failure message: " + JSON.stringify({
  	  	      data : data
  	  	    }));
  	  	  });
  	  	 
  	  	};*/
  	
    $scope.groups = [
                     {
                       title: 'Dynamic Group Header - 1',
                       content: 'Dynamic Group Body - 1'
                     },
                     {
                       title: 'Dynamic Group Header - 2',
                       content: 'Dynamic Group Body - 2'
                     }
                   ];
                
  	
  	
  	
  	
  	
  	
  	           /*POPULATE DATA TO TABLE*/
  
  	 $scope.selectSummary = function(reponame) {
  		$scope.reponames;
	//JSON.stringify(data);
  		
  		$scope.isImg=true;
     	var response = $http.post('/PAAS-GUI/rest/applicationService/getApplicationSummary',reponame);
     	
     	response.success(function(data){
     		$scope.isImg=false;
     		$scope.reponames = data;
     		console.log("selectRepo >>>> "+$scope.reponames);
     	});
     	response.error(function(data, status, headers, config) {
                 alert("Error in Fetching Data");
         });
     };
     //store  services
     
     $scope.storeServices = function() {
    	
    
    	 angular.forEach($scope.scales,function(value){
     		
    		 $scope.scale.scales.push(value);
             
               
           })
           
    	  angular.forEach($scope.env,function(value){
     		
    		 $scope.scale.env.push(value);
             
               
           })
     	 angular.forEach($scope.route,function(value){
     		
    		 $scope.scale.route.push(value);
             
               
           })
    
    	   userData = angular.toJson($scope.scale);
    	  //alert("userData "+userData);
    	  //alert("scales <>"+$scope.env);
    	 // var userData2 = angular.toJson($scope.env);
    	 //alert(userData2);
    	//var total = angular.merge({},userData,userData2);
    	//alert("total : "+total);
    	//alert("scale "+$scope.scale);
     	  var res = $http.post('/PAAS-GUI/rest/applicationService/addService',userData);
     	 
     	  res.success(function(data, status, headers, config) {
     		  console.log($scope.scale);
     	    $scope.message = data;
     	  // alert("$scope.storeServices"+ $scope.message);
     	  $scope.selectServices();
     	    
     	  });
     	  res.error(function(data, status, headers, config) {
     	    alert("failure message: " + JSON.stringify({
     	      data : data
     	    }));
     	  });
     	 
     	};
     //For  Add  Service
     
     $scope.selectServices = function() {
      	var response = $http.get('/PAAS-GUI/rest/applicationService/getAllApplicationService');
      	response.success(function(data){
      		
      		$scope.services = data;
      		console.log("jjjjjjjj "+$scope.services);
      	});
      	response.error(function(data, status, headers, config) {
                  alert("Error in Fetching Data");
          });
      };
    
      	
     
     /*for table*/
     $scope.addNewChoice = function() {
    	   
    	    $scope.scales.push({portname:'',porttype:'',hostport:'',containerport:''});
    	  };
    	  
    	  
    	  $scope.removeChoice = function(index) {
    		  
    		    $scope.scales.splice(index,1);
    		  };
     
     
     /*for table*/
    		  //Envirnament  add
    		  $scope.addNewEnvirnament = function() {
    	    	   
    	    	    $scope.env.push({envkey:'',envvalue:''});
    	    	  };
    	    	  
    	    	  
    	    	  $scope.removeEnvirnament = function(index) {
    	    		  
    	    		    $scope.env.splice(index,1);
    	    		  };
    	    		  
    	    		  //Route  add
    	    		  $scope.addNewRoute = function() {
    	    	    	   
    	    	    	    $scope.route.push({type:'',port:'',routetype:'',target:''});
    	    	    	  };
    	    	    	 
    	    	    	  
    	    	    	  $scope.removeRoute = function(index) {
    	    	    		  
    	    	    		    $scope.route.splice(index,1);
    	    	    		  };
     
     
     
     $scope.selectSummarydata = function() {
      	var response = $http.get('/PAAS-GUI/rest/applicationService/getAllApplicationSummary');
      	
      	response.success(function(data){
      		$scope.fields = data;
      		//console.log("selectSummarydata() "+$scope.fields);
      	});
      	response.error(function(data, status, headers, config) {
                  alert("Error in Fetching Data");
          });
      };
     
     
     
  	         /*DELETE POPULATED DATA*/
  	//commented as this facility is not provided

    /* $scope.deleteData = function(data) {
     	var response = $http.get('/PAAS-GUI/rest/fetchData/deleteData/'+data);
     	response.success(function(data){
     		$scope.select();
     	});
     	response.error(function(data, status, headers, config) {
                 //alert("Error in Fetching Data");
         });
     	
     };
     
              EDIT VPC DATA
     $scope.update = function(data) {
     	var response = $http.get('/PAAS-GUI/rest/fetchData/updata/'+data);
     	response.success(function(data){
     		$scope.select();
     	});
     	response.error(function(data, status, headers, config) {
                 //alert("Error in Fetching Data");
         });
     };*/
     
     /*Delete Service By Name*/
     $scope.deleteServiceByName = function (data) {
    	 alert("coming to deleteServiceByName"+data);
    	var response = $http.get('/PAAS-GUI/rest/applicationService/deleteServiceByName/'+data);
      	response.success(function(data){
      		$scope.select();
      	});
      	response.error(function(data, status, headers, config) {
                  //alert("Error in Fetching Data");
          });
     };
    
  	
     $scope.scaling = function () {
    	
    	 
    	
    	 alert("coming to deleteServiceByName"+ $scope.scaling.instance);
    	var response = $http.put('/PAAS-GUI/rest/applicationService/updateMarathonInstace/',$scope.scaling.instance);
      	response.success(function(data){
      		
      	});
      	response.error(function(data, status, headers, config) {
      		alert("error"+data);
                  //alert("Error in Fetching Data");
          });
     };

    
});  /*end of controller*/


myvpc.directive('modal', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog modal-lg">' + 
            '<div class="modal-content" >' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
    	$('.continue').click(function() {
			$('.nav-tabs > .active').next('li').find('a').trigger('click');
		});
		$('.back').click(function() {
			$('.nav-tabs > .active').prev('li').find('a').trigger('click');
		});
		$('.cancel').click(function() {
			$('.nav-tabs > .active').cancel('li').find('a').trigger('click');
		});
        
      }
    };
  });

myvpc.directive('scaling', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog modal-lg">' + 
            '<div class="modal-content" >' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
    	$('.continue').click(function() {
			$('.nav-tabs > .active').next('li').find('a').trigger('click');
		});
		$('.back').click(function() {
			$('.nav-tabs > .active').prev('li').find('a').trigger('click');
		});
		$('.cancel').click(function() {
			$('.nav-tabs > .active').cancel('li').find('a').trigger('click');
		});
        
      }
    };
  });
