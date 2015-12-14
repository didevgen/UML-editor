/**
 * Created by maxim on 13.12.15.
 */

angular.module('jumlitApp').service('PngExport', function (FileSaver) {

    if (!HTMLCanvasElement.prototype.toBlob) {
        Object.defineProperty(HTMLCanvasElement.prototype, 'toBlob', {
            value: function (callback, type, quality) {

                var binStr = atob(this.toDataURL(type, quality).split(',')[1]),
                    len = binStr.length,
                    arr = new Uint8Array(len);

                for (var i = 0; i < len; i++) {
                    arr[i] = binStr.charCodeAt(i);
                }

                callback(new Blob([arr], {type: type || 'image/png'}));
            }
        });
    }

    return {
        export: function (name) {
            var diagramSvg = $('diagram-canvas svg');
            var diagramSvgCopy =diagramSvg.clone();
            diagramSvgCopy.find('.marker-arrowhead-group, .tool-remove, .tool-options').remove();
            var svgStr = diagramSvgCopy.prop('outerHTML');
            var canvas = document.createElement('canvas');
            canvg(canvas, svgStr, {
                ignoreMouse: true,
                ignoreAnimation: true,
                renderCallback: function() {

                    var parent = diagramSvg.parent();
                    diagramSvg.remove();
                    parent.append(canvas);

                    html2canvas($('.diagram-canvas'), {
                        background: "#fff",
                        onrendered: function (canvas) {
                            var context = canvas.getContext("2d");

                            // set to draw behind current content
                            context.globalCompositeOperation = "destination-over";

                            // set background color
                            context.fillStyle = '#fff'; // <- background color

                            // draw background / rect on entire canvas
                            context.fillRect(0, 0, canvas.width, canvas.height);

                            canvas.toBlob(function (blob) {
                                FileSaver.saveAs(blob, name + '.png', 1);
                            });
                            $('diagram-canvas canvas').remove();
                            parent.append(diagramSvg);
                        }
                    });
                }
            });
        }
    };
});
