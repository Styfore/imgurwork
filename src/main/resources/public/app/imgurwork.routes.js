'use_strict';
(function(){
  
    angular.module("imgurwork")
        .config(["$urlRouterProvider", "$stateProvider", "$urlMatcherFactoryProvider",  function ($urlRouterProvider, $stateProvider, $urlMatcherFactoryProvider) {
            
        	$urlMatcherFactoryProvider.strictMode(false)
        	
            $urlRouterProvider.otherwise('/oups');
            $stateProvider
            .state(
                'home',
                {
                    url: "/oups",
                    templateUrl: 'app/components/home/homeView.html',
                    controller: 'homeController'    
                })
            .state(
                'link',
                {
                    url: "/link",
                    templateUrl: 'app/components/link/linkView.html',
                    controller: 'linkController'    
                });
        }]);

})();
