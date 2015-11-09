'use strict';
angular.module('jumlitApp').service('Authorization', function(Session, $state) {
    return {
        authorize: function(event, data) {
            if(data.authenticated && !Session.authenticated) {
                event.preventDefault();
                $state.go('landing.login');
                console.log(data.authenticated && !Session.authenticated);
            }
        }
    };
});
