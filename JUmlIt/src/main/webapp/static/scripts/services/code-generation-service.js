/**
 * Created by maxim on 13.12.15.
 */

angular.module('jumlitApp').service('CodeGeneration', function (FileSaver, Config) {
    return {
        generate: function (diagramId) {
            window.open(Config.API_PATH + 'code/generate/' + diagramId);
        }
    };
});
