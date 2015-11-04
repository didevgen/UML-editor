'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');
var sourcemaps = require('gulp-sourcemaps');
var bower = require('gulp-bower');
var wiredep = require('wiredep');
var inject = require('gulp-inject');

var webappPath = 'src/main/webapp/static/';

gulp.task('sass', function() {
    gulp.src(webappPath + 'styles/sass/**/*.scss')
        .pipe(sourcemaps.init())
        .pipe(sass().on('error', sass.logError))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(webappPath + 'styles/css'));
});

gulp.task('index', function() {
    var sources = [webappPath + 'styles/css/**/*.css', '!' + webappPath + 'scripts/bower_components/**/*', webappPath + 'scripts/**/*.js'];
    wiredep({
        src: webappPath + 'index.html'
    });
    gulp.src(webappPath + 'index.html')
        .pipe(inject(gulp.src(sources), {
            relative: true
        }))
        .pipe(gulp.dest(webappPath));
});

gulp.task('bower', function() {
    return bower();
});

gulp.task('sass:watch', function() {
    gulp.watch(webappPath + 'styles/sass/**/*.scss', ['sass']);
});

gulp.task('index:watch', function() {
    gulp.watch(webappPath + 'styles/css/**/*.css', ['index']);
    gulp.watch(webappPath + 'scripts/**/*.js', ['index']);
});

gulp.task('watch', ['index:watch', 'sass:watch'], function() {});

gulp.task('default', ['watch', 'index'], function() {});
