package com.sample.model;

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
@Table(name = "Artist")
public class Artist {

	@OneToMany(mappedBy = "artistId")
    private Set<Album> albums;

	@Column(name = "Name", length = 120)
    private String name;

	public Set<Album> getAlbums() {
        return albums;
    }

	public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ArtistId")
    private Integer artistId;

	public Integer getArtistId() {
        return this.artistId;
    }

	public void setArtistId(Integer id) {
        this.artistId = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new Artist().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countArtists() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Artist o", Long.class).getSingleResult();
    }

	public static List<Artist> findAllArtists() {
        return entityManager().createQuery("SELECT o FROM Artist o", Artist.class).getResultList();
    }

	public static Artist findArtist(Integer artistId) {
        if (artistId == null) return null;
        return entityManager().find(Artist.class, artistId);
    }

	public static List<Artist> findArtistEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Artist o", Artist.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Artist attached = Artist.findArtist(this.artistId);
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
    public Artist merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Artist merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
