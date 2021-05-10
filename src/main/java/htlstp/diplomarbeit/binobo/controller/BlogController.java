package htlstp.diplomarbeit.binobo.controller;

import htlstp.diplomarbeit.binobo.controller.util.FlashMessage;
import htlstp.diplomarbeit.binobo.model.Post;
import htlstp.diplomarbeit.binobo.model.User;
import htlstp.diplomarbeit.binobo.service.PostService;
import htlstp.diplomarbeit.binobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/blog")
    public String listAllBlogs(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);

        return "blog/blogOverview";
    }

    @RequestMapping(value = "/blog/{postId}")
    public String postX(@PathVariable Long postId, Model model){
        Post post = postService.findById(postId);
        model.addAttribute("post", post);

        return "blog/blog_PostX";
    }

    @RequestMapping(value = "/blog/new")
    public String newBlogPost(Model model){
        if(!model.containsAttribute("post")){
            model.addAttribute("post", new Post());
        }
        model.addAttribute("heading", "new BlogPost()");
        model.addAttribute("submit", "Upload");
        model.addAttribute("action", "/blog");

        return "blog/blogForm";
    }

    @RequestMapping(value = "/blog/{blogId}/edit")
    public String formEditBlogEntry(@PathVariable Long blogId, Model model){
        if(!model.containsAttribute("post")){
            model.addAttribute("post", postService.findById(blogId));
        }
        model.addAttribute("heading", "blogEntry.edit()");
        model.addAttribute("action", String.format("/blog/%s", blogId));
        model.addAttribute("submit", "Update");

        return "blog/blogForm";
    }

    @RequestMapping(value = "/blog/{blogId}", method = RequestMethod.POST)
    public String updateBlogEntry(@Valid Post post, Errors bindingResult, RedirectAttributes redirectAttributes){// BindingResults

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post",bindingResult);
            redirectAttributes.addFlashAttribute("post",post);

            return String.format("redirect:/blog/%s/edit",post.getId());
        }

        post.setUpdatedOn(LocalDateTime.now());

        postService.savePost(post);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Successfully added new Entry!", FlashMessage.Status.SUCCESS));

        return "redirect:/blog";
    }

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public String addBlogEntry(@Valid Post post, Errors bindingResult, RedirectAttributes redirectAttributes, Principal principal){// BindingResults

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post",bindingResult);
            redirectAttributes.addFlashAttribute("post",post);

            return "redirect:/blog/new";
        }
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        post.setUser(user);
        post.setUsername(user.getUsername());

        postService.savePost(post);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Post successfully uploaded!", FlashMessage.Status.SUCCESS));
        return "redirect:/blog";
    }

}
