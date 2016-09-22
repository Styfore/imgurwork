'use_strict';
(function(){
  
    angular.module("imgurwork")
        .config(["$urlRouterProvider", "$stateProvider", "$urlMatcherFactoryProvider",  function ($urlRouterProvider, $stateProvider, $urlMatcherFactoryProvider) {
            
        	$urlMatcherFactoryProvider.strictMode(false)
        	
            $urlRouterProvider.otherwise('/');
            $stateProvider
            .state(
                'home',
                {
                    url: "/",
                    templateUrl: 'app/components/home/homeView.html',
                    controller: 'homeController'    
                })
            .state(
                'image',
                {
                    url: "/image",
                    templateUrl: 'app/components/image/imageView.html',
                    controller: 'imageController'    
                });
        }]);

})();
