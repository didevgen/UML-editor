/**
 * Created by maxim on 06.12.15.
 */

angular.module('jumlitApp').service('Links', function() {

    var uml = joint.shapes.uml;

    var links = {
        Association: new joint.dia.Link()
    };

    return {
        create: function(type, source, target) {
            var link = cells[type];
            link.set('source', source);
            link.set('target', target);
            return link.clone();
        }
    };
});
