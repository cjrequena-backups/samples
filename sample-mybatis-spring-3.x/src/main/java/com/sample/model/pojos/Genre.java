package com.sample.model.pojos;

public class Genre {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Genre.GenreId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer genreid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Genre.Name
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Genre.GenreId
     *
     * @return the value of Genre.GenreId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getGenreid() {
        return genreid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Genre.GenreId
     *
     * @param genreid the value for Genre.GenreId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setGenreid(Integer genreid) {
        this.genreid = genreid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Genre.Name
     *
     * @return the value of Genre.Name
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Genre.Name
     *
     * @param name the value for Genre.Name
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}