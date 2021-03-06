package com.dragonlz.oxygenread.UI.Model;

import java.util.List;

/**
 * Created by sdm on 2015/8/21.
 */
public class Movie {

    private String cinema;
    private String cinemaPhone;
    private String cinemaAddress;
    private List movie;

    public List getMovie() {
        return movie;
    }

    public void setMovie(List movie) {
        this.movie = movie;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }



    /**
     *
     *
     *
     *
     */

    public class MovieContent {
        private String commentTime;
        private String todayTime;
        private String movieStartTime;
        private String movieName;
        private String movieType;//电影类型。（2D还是3D。。）
        private String movieProduceArea;
        private String movieDirector;
        private String movieStar;
        private String movieFirstDate;
        private String moviePicture;
        private String movieLength;
        private String movieDescrption;
        private String movieComment;
        private String movieStyle;//电影类型（爱情片，喜剧。。。）
        private String movieMessage;//主演剧中要演的故事情节

        private List movieContent;

        public List getMovieContent() {
            return movieContent;
        }

        public void setMovieContent(List movieContent) {
            this.movieContent = movieContent;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public String getTodayTime() {
            return todayTime;
        }

        public void setTodayTime(String todayTime) {
            this.todayTime = todayTime;
        }

        public String getMovieStartTime() {
            return movieStartTime;
        }

        public void setMovieStartTime(String movieStartTime) {
            this.movieStartTime = movieStartTime;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getMovieProduceArea() {
            return movieProduceArea;
        }

        public void setMovieProduceArea(String movieProduceArea) {
            this.movieProduceArea = movieProduceArea;
        }

        public String getMovieDirector() {
            return movieDirector;
        }

        public void setMovieDirector(String movieDirector) {
            this.movieDirector = movieDirector;
        }

        public String getMovieStar() {
            return movieStar;
        }

        public void setMovieStar(String movieStar) {
            this.movieStar = movieStar;
        }

        public String getMovieFirstDate() {
            return movieFirstDate;
        }

        public void setMovieFirstDate(String movieFirstDate) {
            this.movieFirstDate = movieFirstDate;
        }

        public String getMoviePicture() {
            return moviePicture;
        }

        public void setMoviePicture(String moviePicture) {
            this.moviePicture = moviePicture;
        }

        public String getMovieLength() {
            return movieLength;
        }

        public void setMovieLength(String movieLength) {
            this.movieLength = movieLength;
        }

        public String getMovieDescrption() {
            return movieDescrption;
        }

        public void setMovieDescrption(String movieDescrption) {
            this.movieDescrption = movieDescrption;
        }

        public String getMovieComment() {
            return movieComment;
        }

        public void setMovieComment(String movieComment) {
            this.movieComment = movieComment;
        }

        public String getMovieStyle() {
            return movieStyle;
        }

        public void setMovieStyle(String movieStyle) {
            this.movieStyle = movieStyle;
        }

        public String getMovieMessage() {
            return movieMessage;
        }

        public void setMovieMessage(String movieMessage) {
            this.movieMessage = movieMessage;
        }



        public class MovieDescendants {
            private String moviePlayTime;
            private String moviePlayDate;

            public String getMoviePlayTime() {
                return moviePlayTime;
            }

            public void setMoviePlayTime(String moviePlayTime) {
                this.moviePlayTime = moviePlayTime;
            }

            public String getMoviePlayDate() {
                return moviePlayDate;
            }

            public void setMoviePlayDate(String moviePlayDate) {
                this.moviePlayDate = moviePlayDate;
            }
        }

    }

}
