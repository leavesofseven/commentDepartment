package ssh.product.model.trends;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "comment", schema = "fxweb")
public class CommentEntity {

        private int id;
        private int trends_id;
        private String content;
        private int user_id;
        private Time comment_time;
        private int parent;

        public CommentEntity(){}

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public int getTrends_id() {
        return trends_id;
    }

    public void setTrends_id(int trends_id) {
        this.trends_id = trends_id;
    }

    @Basic
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Basic
    public Time getComment_time() {
        return comment_time;
    }

    public void setComment_time(Time comment_time) {
        this.comment_time = comment_time;
    }

    @Basic
    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
