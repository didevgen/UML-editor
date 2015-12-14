/**
 * Created by maxim on 30.11.15.
 */

'use strict';
angular.module('jumlitApp').service('UserServices', function(Utils) {
    return {
        findUsersByEmail: function(email) {
            return Utils.postRequest('account/email/'+email);
        },
        updateUser: function(user) {
            return Utils.postRequest('/account/'+ user.userId +'/update', user);
        }
    }
});
