package com.sample.model.jpa;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "Genre")
public class Genre {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@OneToMany(mappedBy = "genreId")
    private Set<Track> tracks;

	@Column(name = "Name", length = 120)
    private String name;

	public Set<Track> getTracks() {
        return tracks;
    }

	public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new Genre().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countGenres() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Genre o", Long.class).getSingleResult();
    }

	public static List<Genre> findAllGenres() {
        return entityManager().createQuery("SELECT o FROM Genre o", Genre.class).getResultList();
    }

	public static Genre findGenre(Integer genreId) {
        if (genreId == null) return null;
        return entityManager().find(Genre.class, genreId);
    }

	public static List<Genre> findGenreEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Genre o", Genre.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Genre attached = Genre.findGenre(this.genreId);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Genre merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Genre merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GenreId")
    private Integer genreId;

	public Integer getGenreId() {
        return this.genreId;
    }

	public void setGenreId(Integer id) {
        this.genreId = id;
    }
}
