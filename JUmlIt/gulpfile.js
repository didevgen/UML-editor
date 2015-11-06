'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');
var bower = require('gulp-bower');
var wiredep = require('wiredep');
var inject = require('gulp-inject');
var concat = require('gulp-concat');
var browserSync = require('browser-sync').create();
var sourcemaps = require('gulp-sourcemaps');

var webappPath = 'src/main/webapp/static/';

gulp.task('sass', function() {
    gulp.src(webappPath + 'styles/sass/main.scss')
        .pipe(sourcemaps.init())
        .pipe(sass().on('error', sass.logError))
		.pipe(concat('main.css'))
        .pipe(gulp.dest(webappPath + 'styles/css'))
        .pipe(browserSync.stream());
});

gulp.task('index', function() {
    var sources = [webappPath + 'styles/css/**/*.css', '!' + webappPath + 'scripts/bower_components/**/*', '!' + webappPath + 'scripts/app.js',
		webappPath + 'scripts/**/*.js'];
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

gulp.task('serve', ['index'], function() {
    browserSync.init({
        server: webappPath
    })
});

gulp.task('watch', ['index', 'serve'], function() {
    gulp.watch(webappPath + 'styles/sass/**/*.scss', ['sass']);
    gulp.watch(webappPath + 'styles/css/**/*.css', ['index']);
    gulp.watch(webappPath + 'scripts/**/*.js', ['index']);
    gulp.watch(webappPath + '**/*.html').on('change', browserSync.reload);
    gulp.watch(webappPath + '**/*.js').on('change', browserSync.reload);
});

gulp.task('default', ['watch'], function() {});
