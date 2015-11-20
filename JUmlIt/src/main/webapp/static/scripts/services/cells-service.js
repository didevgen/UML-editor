'use strict';
/* globals joint:true */
angular.module('jumlitApp').service('Cells', function() {

    joint.shapes.jumlit = {};
    joint.shapes.jumlit.Class = joint.shapes.basic.Rect.extend({
        defaults: joint.util.deepSupplement({
            type: 'jumlit.Class',
            attrs: {
                rect: {
                    stroke: 'none',
                    'fill-opacity': 0
                }
            },
            methods: [],
            attributes: []
        }, joint.shapes.basic.Rect.prototype.defaults)
    });

    var uml = joint.shapes.jumlit;
    var cells = {
        Class: new uml.Class({
            size: {
                width: 220,
                height: 220
            },
        })
    };

    return {
        create: function(type, position, name) {
            var cell = cells[type];
            cell.set('name', name);
            cell.set('position', position);
            return cell.clone();
        }
    };
});
