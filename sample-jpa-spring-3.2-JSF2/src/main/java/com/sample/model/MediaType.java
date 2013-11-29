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

@Entity
@Table(name = "MediaType")
@Configurable
public class MediaType {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MediaTypeId")
    private Integer mediaTypeId;

	public Integer getMediaTypeId() {
        return this.mediaTypeId;
    }

	public void setMediaTypeId(Integer id) {
        this.mediaTypeId = id;
    }

	@OneToMany(mappedBy = "mediaTypeId")
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
        EntityManager em = new MediaType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMediaTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MediaType o", Long.class).getSingleResult();
    }

	public static List<MediaType> findAllMediaTypes() {
        return entityManager().createQuery("SELECT o FROM MediaType o", MediaType.class).getResultList();
    }

	public static MediaType findMediaType(Integer mediaTypeId) {
        if (mediaTypeId == null) return null;
        return entityManager().find(MediaType.class, mediaTypeId);
    }

	public static List<MediaType> findMediaTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MediaType o", MediaType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            MediaType attached = MediaType.findMediaType(this.mediaTypeId);
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
    public MediaType merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MediaType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
