package com.sample.model.jpa;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "Album")
public class Album {

	@OneToMany(mappedBy = "albumId")
    private Set<Track> tracks;

	@ManyToOne
    @JoinColumn(name = "ArtistId", referencedColumnName = "ArtistId", nullable = false)
    private Artist artistId;

	@Column(name = "Title", length = 160)
    @NotNull
    private String title;

	public Set<Track> getTracks() {
        return tracks;
    }

	public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

	public Artist getArtistId() {
        return artistId;
    }

	public void setArtistId(Artist artistId) {
        this.artistId = artistId;
    }

	public String getTitle() {
        return title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new Album().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countAlbums() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Album o", Long.class).getSingleResult();
    }

	public static List<Album> findAllAlbums() {
        return entityManager().createQuery("SELECT o FROM Album o", Album.class).getResultList();
    }

	public static Album findAlbum(Integer albumId) {
        if (albumId == null) return null;
        return entityManager().find(Album.class, albumId);
    }

	public static List<Album> findAlbumEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Album o", Album.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Album attached = Album.findAlbum(this.albumId);
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
    public Album merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Album merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AlbumId")
    private Integer albumId;

	public Integer getAlbumId() {
        return this.albumId;
    }

	public void setAlbumId(Integer id) {
        this.albumId = id;
    }
}
