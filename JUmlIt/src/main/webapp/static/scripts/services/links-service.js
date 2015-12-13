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

    var links = {};
    links[Enums.relationshipTypes.AGGREGATION] = joint.shapes.uml.Aggregation;
    links[Enums.relationshipTypes.ASSOCIATION] = joint.shapes.uml.Association;
    links[Enums.relationshipTypes.COMPOSITION] = joint.shapes.uml.Composition;
    links[Enums.relationshipTypes.DEPENDENCY] = joint.shapes.uml.Association;
    links[Enums.relationshipTypes.GENERALIZATION] = joint.shapes.uml.Generalization;
    links[Enums.relationshipTypes.REALIZATION] = joint.shapes.uml.Implementation;

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
                    text: text || ''
                }
            }
        });
    }

    return {
        create: function (type, source, target) {
            var link = new links[type]({
                labels: labels
            });
            if (source) link.set('source', source);
            if (target) link.set('target', target);

            return link.clone();
        },
        setLabel: function(link, labelPos, value) {
            setLabel(link, labelIndeces[labelPos], value)
        }
    };
});
