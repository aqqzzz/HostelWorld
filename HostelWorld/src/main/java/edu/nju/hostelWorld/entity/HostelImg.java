package edu.nju.hostelWorld.entity;

import javax.persistence.*;

/**
 * Created by 张文玘 on 2017/3/18.
 */
@Entity
@Table(name = "hostel_img" , schema = "hostelworld")
public class HostelImg {
    private int id;
    private String url;
    private Hostel hostelByHostelId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    @Basic
    @Column(name = "url", nullable = true)
    public String getUrl(){ return url; }

    public void setUrl(String url){ this.url = url; }

    @ManyToOne
    @JoinColumn(name="hostel_id", referencedColumnName = "id", nullable = false)
    public Hostel getHostelByHostelId(){ return hostelByHostelId; }

    public void setHostelByHostelId(Hostel hostelByHostelId){ this.hostelByHostelId = hostelByHostelId; }

}
