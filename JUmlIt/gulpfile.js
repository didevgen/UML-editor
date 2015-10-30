'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');
var sourcemaps = require('gulp-sourcemaps');
var bower = require('gulp-bower');
var wiredep = require('wiredep');

var webappPath = 'src/main/webapp/';

gulp.task('sass', function () {
	gulp.src(webappPath + 'styles/sass/**/*.scss')
		.pipe(sourcemaps.init())
		.pipe(sass().on('error', sass.logError))
		.pipe(sourcemaps.write())
		.pipe(gulp.dest(webappPath + 'styles/css'));
});

gulp.task('index', function () {
	wiredep({ src: webappPath + 'index.html' });
});

gulp.task('bower', function() {
	return bower();
});

gulp.task('sass:watch', function () {
	gulp.watch(webappPath + 'styles/sass/**/*.scss', ['sass']);
});

gulp.task('default', ['sass:watch', 'index'], function () {
});
