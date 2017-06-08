/*package onlinecollaborationbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ali.onlinecollaborationbackend.Dao.BlogDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.initializer.MvcWebApplicationInitializer;
import com.ali.onlinecollaborationbackend.model.Blog;
import com.ali.onlinecollaborationbackend.model.User;

public class BlogTest {

	private static Blog blog;
	@Autowired
	private static BlogDao blogDao;

	private static User user;
	@Autowired
	private static UserDao userDao;

	@BeforeClass
	public static void init() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MvcWebApplicationInitializer.class);
		context.scan("com.ali");
		context.refresh();
		user = (User) context.getBean("user");
		blog = (Blog) context.getBean("blog");
		blogDao = (BlogDao) context.getBean("blogDao");
		userDao = (UserDao) context.getBean("userDao");
	}

	@Test
	public void addBlogTestcase() {

		blog.setTitle("Posting blog 4");
		blog.setDescription("Third blog");
		blog.setStatus('B');
		blog.setReason("asdadsdfsdffef");
		blog.setLikes(25);
		blog.setViews(2345);
		blog.setUser(userDao.getUserById(21));

		boolean flag = blogDao.addBlog(blog);
		assertEquals("addBlogTestcase", true, flag);

	}

}
*/