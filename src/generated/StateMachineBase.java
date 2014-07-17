/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("WebBrowser", com.codename1.components.WebBrowser.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("WebBrowser", com.codename1.components.WebBrowser.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Label findTitle(Component root) {
        return (com.codename1.ui.Label)findByName("title", root);
    }

    public com.codename1.ui.Label findTitle() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("title", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("title", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.WebBrowser findVideoBrowser(Component root) {
        return (com.codename1.components.WebBrowser)findByName("videoBrowser", root);
    }

    public com.codename1.components.WebBrowser findVideoBrowser() {
        com.codename1.components.WebBrowser cmp = (com.codename1.components.WebBrowser)findByName("videoBrowser", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.WebBrowser)findByName("videoBrowser", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findVideoCellRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("VideoCellRenderer", root);
    }

    public com.codename1.ui.Container findVideoCellRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("VideoCellRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("VideoCellRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findUploader(Component root) {
        return (com.codename1.ui.Label)findByName("uploader", root);
    }

    public com.codename1.ui.Label findUploader() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("uploader", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("uploader", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStaticOn(Component root) {
        return (com.codename1.ui.Label)findByName("staticOn", root);
    }

    public com.codename1.ui.Label findStaticOn() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("staticOn", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("staticOn", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStaticUploadedBy(Component root) {
        return (com.codename1.ui.Label)findByName("staticUploadedBy", root);
    }

    public com.codename1.ui.Label findStaticUploadedBy() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("staticUploadedBy", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("staticUploadedBy", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findThumbnailURLImage(Component root) {
        return (com.codename1.ui.Label)findByName("thumbnail_URLImage", root);
    }

    public com.codename1.ui.Label findThumbnailURLImage() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("thumbnail_URLImage", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("thumbnail_URLImage", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findUploadDate(Component root) {
        return (com.codename1.ui.Label)findByName("uploadDate", root);
    }

    public com.codename1.ui.Label findUploadDate() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("uploadDate", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("uploadDate", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findVideoList(Component root) {
        return (com.codename1.ui.List)findByName("videoList", root);
    }

    public com.codename1.ui.List findVideoList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("videoList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("videoList", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(f.getName())) {
            exitVideoPlayer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(f.getName())) {
            exitVideoCellRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitMain(Form f) {
    }


    protected void exitVideoPlayer(Form f) {
    }


    protected void exitVideoCellRenderer(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(f.getName())) {
            beforeVideoPlayer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(f.getName())) {
            beforeVideoCellRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeMain(Form f) {
    }


    protected void beforeVideoPlayer(Form f) {
    }


    protected void beforeVideoCellRenderer(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(c.getName())) {
            beforeContainerVideoPlayer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(c.getName())) {
            beforeContainerVideoCellRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerMain(Container c) {
    }


    protected void beforeContainerVideoPlayer(Container c) {
    }


    protected void beforeContainerVideoCellRenderer(Container c) {
    }

    protected void postShow(Form f) {
        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(f.getName())) {
            postVideoPlayer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(f.getName())) {
            postVideoCellRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postMain(Form f) {
    }


    protected void postVideoPlayer(Form f) {
    }


    protected void postVideoCellRenderer(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(c.getName())) {
            postContainerVideoPlayer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(c.getName())) {
            postContainerVideoCellRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerMain(Container c) {
    }


    protected void postContainerVideoPlayer(Container c) {
    }


    protected void postContainerVideoCellRenderer(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(rootName)) {
            onCreateVideoPlayer();
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(rootName)) {
            onCreateVideoCellRenderer();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateMain() {
    }


    protected void onCreateVideoPlayer() {
    }


    protected void onCreateVideoCellRenderer() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("VideoPlayer".equals(f.getName())) {
            getStateVideoPlayer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("VideoCellRenderer".equals(f.getName())) {
            getStateVideoCellRenderer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateMain(Form f, Hashtable h) {
    }


    protected void getStateVideoPlayer(Form f, Hashtable h) {
    }


    protected void getStateVideoCellRenderer(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoPlayer".equals(f.getName())) {
            setStateVideoPlayer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoCellRenderer".equals(f.getName())) {
            setStateVideoCellRenderer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateMain(Form f, Hashtable state) {
    }


    protected void setStateVideoPlayer(Form f, Hashtable state) {
    }


    protected void setStateVideoCellRenderer(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("videoList".equals(listName)) {
            return initListModelVideoList(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelVideoList(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("Main")) {
            if("videoList".equals(c.getName())) {
                onMain_VideoListAction(c, event);
                return;
            }
        }
    }

      protected void onMain_VideoListAction(Component c, ActionEvent event) {
      }

}
