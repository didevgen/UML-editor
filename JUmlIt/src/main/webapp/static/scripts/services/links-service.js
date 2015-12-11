/**
 * Created by maxim on 06.12.15.
 */

angular.module('jumlitApp').service('Links', function (Enums) {

    var defaultAttrs = {
        '.connection': {
            'stroke-dasharray': '0 0'
        },
        '.marker-target': {
            d: ''
        },
        '.marker-source': {
            d: ''
        }
    };

    var attrs = {
        association: defaultAttrs,
        dependency: defaultAttrs,
        generalization: {
            '.marker-target': {d: 'M 20 0 L 0 10 L 20 20 z', fill: 'white'}
        },
        realization: {
            '.marker-target': {d: 'M 20 0 L 0 10 L 20 20 z', fill: 'white'},
            '.connection': {'stroke-dasharray': '3,3'}
        },
        aggregation: {
            '.marker-target': {d: 'M 40 10 L 20 20 L 0 10 L 20 0 z', fill: 'white'}
        },
        composition: {
            '.marker-target': {d: 'M 40 10 L 20 20 L 0 10 L 20 0 z', fill: 'black'}
        }
    };

    var labels = [{
        position: +14,
        attrs: {
            text: {
                text: ''
            }
        }
    }, {
        position: +15,
        attrs: {
            text: {
                text: ''
            }
        }
    }, {
        position: .5,
        attrs: {
            text: {
                text: ''
            }
        }
    }, {
        position: -14,
        attrs: {
            text: {
                text: ''
            }
        }
    }, {
        position: -15,
        attrs: {
            text: {
                text: ''
            }
        }
    }];

    var labelIndeces = {};
    labelIndeces[Enums.linkLabels.TARGET] = 0;
    labelIndeces[Enums.linkLabels.TARGET_BELOW] = 1;
    labelIndeces[Enums.linkLabels.CENTER] = 2;
    labelIndeces[Enums.linkLabels.SOURCE] = 3;
    labelIndeces[Enums.linkLabels.SOURCE_BELOW] = 4;

    function setLabel(link, index, text) {
        link.label(index, {
            attrs: {
                text: {
                    text: text
                }
            }
        });
    }

    return {
        create: function (type, source, target) {
            var link = new joint.dia.Link({
                attrs: attrs[type],
                labels: labels
            });
            link.set('source', source);
            link.set('target', target);
            return link.clone();
        },
        setType: function (link, type) {
            link.attr(attrs[type]);
        },
        setLabel: function(link, labelPos, value) {
            console.log(value === undefined);
            setLabel(link, labelIndeces[labelPos], value)
        }
    };
});
