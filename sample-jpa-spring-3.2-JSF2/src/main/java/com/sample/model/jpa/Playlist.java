package com.sample.model.jpa;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "Playlist")
public class Playlist {

	@ManyToMany(mappedBy = "playlists")
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
        EntityManager em = new Playlist().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPlaylists() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Playlist o", Long.class).getSingleResult();
    }

	public static List<Playlist> findAllPlaylists() {
        return entityManager().createQuery("SELECT o FROM Playlist o", Playlist.class).getResultList();
    }

	public static Playlist findPlaylist(Integer playlistId) {
        if (playlistId == null) return null;
        return entityManager().find(Playlist.class, playlistId);
    }

	public static List<Playlist> findPlaylistEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Playlist o", Playlist.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Playlist attached = Playlist.findPlaylist(this.playlistId);
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
    public Playlist merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Playlist merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PlaylistId")
    private Integer playlistId;

	public Integer getPlaylistId() {
        return this.playlistId;
    }

	public void setPlaylistId(Integer id) {
        this.playlistId = id;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
