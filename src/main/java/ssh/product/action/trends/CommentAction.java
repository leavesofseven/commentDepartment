package ssh.product.action.trends;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import ssh.product.model.trends.CommentEntity;
import ssh.product.model.trends.TrendsEntity;
import ssh.product.service.trends.CommentEntityService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CommentAction extends ActionSupport implements ModelDriven<CommentEntity>{
    private CommentEntity commentEntity=new CommentEntity();
    private CommentEntityService commentEntityService;
    private List<CommentEntity> commentEntityList;

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CommentEntity getModel(){
        if (commentEntity==null){
            commentEntity=new CommentEntity();
        }
        return commentEntity;
    }
    public CommentEntityService getCommentEntityService() {
        return commentEntityService;
    }

    public void setCommentEntityService(CommentEntityService commentEntityService) {
        this.commentEntityService = commentEntityService;
    }
    //显示所有评论
    public String Findlist(){
        HttpServletRequest request =ServletActionContext.getRequest();
        String ctrendid=request.getParameter("trends_id");
        commentEntityList=commentEntityService.updateTrendComment(Integer.parseInt(ctrendid));
       ActionContext.getContext().getValueStack().set("commentlist",commentEntityList );
       //以下为测试输出在控制台的
        System.out.println("Size of the valueStack: " + ActionContext.getContext().getValueStack().findValue("user_id"));
        System.out.println("获取的cid："+ctrendid);
        Iterator it = commentEntityList.iterator();
        while(it.hasNext()){
            CommentEntity stu = (CommentEntity)it.next();
            String name = stu.getContent();//得到了里面的元素的属性了
            System.out.println("action内容:"+stu.getContent());
        }
        return "clist";
    }
    //跳转到删除确认页面
    public String IsDel(){
        HttpServletRequest request =ServletActionContext.getRequest();
        String d=request.getParameter("delId");
        int cDelId=Integer.parseInt(d);
        System.out.println("确认删除前获得的要删除的评论的id"+cDelId);
        ActionContext.getContext().getValueStack().set("cDelId",cDelId );
        return "isDel";
    }
    //删除评论
    public String Delete(){
        HttpServletRequest request =ServletActionContext.getRequest();
        String d=request.getParameter("cDelId");
        int c=Integer.parseInt(d);
        System.out.println("确认删除后获得的要删除的评论的id"+c);
        commentEntity=commentEntityService.findtIdBycId(c);
        int trendId=commentEntity.getTrends_id();
        System.out.println("获得删除评论的所属动态的id："+trendId);
        commentEntityList=commentEntityService.updateTrendComment(trendId);
        ActionContext.getContext().getValueStack().set("commentlist",commentEntityList );
        System.out.println("获得评论数组");
        this.commentEntityService.delComment(c);
        System.out.println("完成删除");
        result="删除成功！";
        return "cdelete";
    }
    //跳转到输入评论内容页面
    public String Write(){
        HttpServletRequest request =ServletActionContext.getRequest();
        String d=request.getParameter("id");
//        int c=Integer.parseInt(d);
//        int rc=commentEntity.getId();
        System.out.println("write:获得被回复评论的id:"+d);
        ActionContext.getContext().getValueStack().set("repliedId",d );
        return "writec";
    }
    //发表评论，获取评论内容及对象写入数据库
    public String WriteTo(){
        //获得当前时间
        Date date= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String str = sdf.format(date);
        java.util.Date date1 = null;
        try {
            date1 = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(date1.getTime());
//        commentEntity.setComment_time(Time.valueOf(str));
        System.out.println("action中获取的time为："+time);

        //获得被回复人id
        HttpServletRequest request =ServletActionContext.getRequest();
        String d=request.getParameter("repliedId");
        String content=commentEntity.getContent();
        System.out.println("write to：获得写的评论内容："+content);
        int c=Integer.parseInt(d);
        System.out.println("write to:获得被回复人的id："+c);
        String t=request.getParameter("wbytrend");
        System.out.println("write:获得被点击的动态的id:"+t);

        //通过判断取得的被回复人id与点击动态列表上取得的动态id哪个不为空，判断点击位置，并进行不同操作
        if (d!=null){
            CommentEntity co=commentEntityService.findtIdBycId(c);
            commentEntity.setTrends_id(co.getTrends_id());
            commentEntity.setParent(co.getUser_id());
        }else  if (t!=null){
            TrendsEntity tuserId=commentEntityService.finduserIdBytId(Integer.parseInt(t)); //查找属于该动态的用户的id
            commentEntity.setTrends_id(Integer.parseInt(t));
            commentEntity.setParent(tuserId.getId());
        }else {
            System.out.println("获取被回复对象失败！");
        }
        //写入数据库
        commentEntity.setContent(content);
//        Map session = ActionContext.getContext().getSession();
//       int userId= (int)session.get("id");
        commentEntity.setUser_id(2);//暂时将自己的id设为2
        commentEntity.setComment_time(time);

        commentEntityService.setComment(commentEntity);

        result="回复成功！";
        return "writeto";
    }
    public String WriteByTrend(){
        HttpServletRequest request =ServletActionContext.getRequest();
        String tId=request.getParameter("id");
        System.out.println("write:获得被回复人的id:"+tId);
        ActionContext.getContext().getValueStack().set("wbytrend",tId );
        return "wTyTrend";
    }

}
