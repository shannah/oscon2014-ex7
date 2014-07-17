/**
 * Your application code goes here
 */

package userclasses;

import ca.weblite.oscon.ex7.Video;
import com.codename1.components.WebBrowser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Display;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {
    
    private java.util.List<Video> videoList;
    private Video selectedVideo;
    private static String feedURL = "https://gdata.youtube.com/feeds/api/videos?q=${query}&max-results=30&v=2&alt=jsonc&orderby=published";
    private String currentQuery;
    private Set<String> favouriteIds;
    private boolean favouritesDirty;
    
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }
    
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    protected void initVars(Resources res) {
        videoList = Collections.synchronizedList(new ArrayList<Video>());
        currentQuery = "xataface";
        favouriteIds = Collections.synchronizedSet(new HashSet<String>());
        favouritesDirty = true;
    }
    
    
    private Set<String> getFavouriteIds(){
        if ( favouritesDirty ){
            favouriteIds.clear();
            if ( Storage.getInstance().exists("favouriteIds")){
                favouriteIds.addAll((java.util.List)Storage.getInstance().readObject("favouriteIds"));
            }
            favouritesDirty = false;
            
        }
        return favouriteIds;
    }
    
    private void addFavourite(Video video){
        if ( !getFavouriteIds().contains(video.getId())){
            getFavouriteIds().add(video.getId());
            ArrayList<Video> favs = new ArrayList<Video>();
            if ( Storage.getInstance().exists("favourites")){
                favs.addAll((java.util.List)Storage.getInstance().readObject("favourites"));
                
                
            } 
            favs.add(video);
            Storage.getInstance().writeObject("favourites", favs);
            Storage.getInstance().writeObject("favouriteIds", getFavouriteIds());
        }
    }
    
    private void removeFavourite(Video video){
        if ( getFavouriteIds().contains(video.getId())){
            getFavouriteIds().remove(video.getId());
            if ( Storage.getInstance().exists("favourites")){
                java.util.List favs = (java.util.List)Storage.getInstance().readObject("favourites");
                Video toRemove = null;
                for ( Object o : favs ){
                    Video v = (Video)o;
                    if ( video.getId().equals(v.getId())){
                        toRemove = v;
                        break;
                    }
                }
                favs.remove(toRemove);
                Storage.getInstance().writeObject("favourites", favs);
                Storage.getInstance().writeObject("favouriteIds", getFavouriteIds());
            }
        }
    }
    
    
    private String getFeedURL(){
        
        return StringUtil.replaceAll(feedURL, "${query}", Util.encodeUrl(currentQuery));
    }
    
    private void updateVideoList(){
        Display.getInstance().invokeAndBlock(new Runnable(){

            public void run() {
                Log.p("Feed URL "+getFeedURL());
                videoList.clear();
                videoList.addAll(fetchVideoList(getFeedURL()));
            }

        });
    }
    
    
    private java.util.List<Video> fetchVideoList(String url){
        final ArrayList<Video> out = new ArrayList<Video>();
        ConnectionRequest req = new ConnectionRequest(){

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser p = new JSONParser();
                
                Map data = p.parseJSON(new InputStreamReader(input, "UTF-8"));
                data = (Map)data.get("data");
                java.util.List<Map> items = (java.util.List<Map>)data.get("items");
                for (Map item : items){
                    Video v = new Video();
                    v.setId((String)item.get("id"));
                    v.setTitle((String)item.get("title"));
                    v.setDescription((String)item.get("description"));
                    Map thumb = (Map)item.get("thumbnail");
                    v.setThumbnailUrl((String)thumb.get("hqDefault"));
                    Map content = (Map)item.get("content");
                    //v.setVideoUrl((String)content.get("5"));
                    v.setVideoUrl("https://www.youtube.com/embed/"+v.getId()+"?feature=player_embedded&html5=1");
                    //Map player = (Map)item.get("player");
                    //v.setVideoUrl((String)player.get("default"));
                    v.setCategory((String)item.get("category"));
                    v.setUploader((String)item.get("uploader"));
                    out.add(v);
                }
            }
            
        };
        req.setUrl(url);
        req.setPost(false);
        //req.setContentType("application/json; charset=\"UTF-8\"");
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return out;
        
    }
    
    


    @Override
    protected boolean initListModelVideoList(List cmp) {
        updateVideoListModel(cmp);
        
        return true;
    }

    private void updateVideoListModel(List cmp){
        final java.util.List<Video> vids = new ArrayList<Video>();
        if ( videoList.isEmpty()){
            updateVideoList();
        }
        vids.addAll(videoList);
        
        cmp.setModel(createListModel(vids));
        cmp.repaint();
    }
    
    private DefaultListModel createListModel(java.util.List<Video> vids ){
        
        
        Map[] data = new Map[vids.size()];
        int i=0;
        for ( Video video : vids ){
            Map item = new HashMap();
            item.put("video", video);
            item.put("staticUploadedBy", "Uploaded by");
            item.put("staticOn", "on");
            item.put("thumbnail_URLImage", video.getThumbnailUrl());
            item.put("thumbnail_URLImageName", video.getId()+"Thumb");
            
            item.put("title", video.getTitle());
            item.put("description", video.getDescription());
            item.put("uploader", video.getUploader());
            item.put("uploadDate", video.getUploadDate());
            item.put("favourite", getFavouriteIds().contains(video.getId()));
            
            //Log.p(item+"");
            data[i++] = item;
            
        }
        return new com.codename1.ui.list.DefaultListModel(data);
    }

    @Override
    protected void beforeMain(Form f) {
        List list = findVideoList(f);
        Container selectedCell = createContainer(fetchResourceFile(), "VideoCellRenderer");
        
        Container unselectedCell = createContainer(fetchResourceFile(), "VideoCellRenderer");
        
        list.setRenderer(new GenericListCellRenderer(selectedCell, unselectedCell));
    }

    @Override
    protected void onMain_VideoListAction(Component c, ActionEvent event) {
        if ( event.getX() > Display.getInstance().getDisplayWidth() * 0.75){
            List l = (List)c;
            Video sel = (Video)((Map)l.getSelectedItem()).get("video");
            if ( getFavouriteIds().contains(sel.getId())){
                removeFavourite(sel);
            } else {
                addFavourite(sel);
            }
            
        } else {
            List l = (List)c;
            selectedVideo = (Video)((Map)l.getSelectedItem()).get("video");
            showForm("VideoPlayer",null);
        }
        
    
    }

    @Override
    protected void beforeVideoPlayer(Form f) {
        WebBrowser wb = findVideoBrowser(f);
        String url = selectedVideo.getVideoUrl();
        //Log.p(url);
        wb.setURL(url);
    }

    @Override
    protected void exitVideoPlayer(Form f) {
        WebBrowser wb = findVideoBrowser(f);
        wb.destroy();
    }

    @Override
    protected void onMain_SearchFieldAction(Component c, ActionEvent event) {
        currentQuery = ((AutoCompleteTextField)c).getText();
        Log.p("Current query is "+currentQuery);
        updateVideoList();
        updateVideoListModel(findVideoList(c));
    
    }

    

   

    @Override
    protected boolean initListModelFavouritesList(List cmp) {
        java.util.List<Video> vids = new ArrayList<Video>();
        if ( Storage.getInstance().exists("favourites")){
            java.util.List favourites = (java.util.List)Storage.getInstance().readObject("favourites");
            vids.addAll(favourites);
        } 
        cmp.setModel(createListModel(vids));
        return true;
    }

    @Override
    protected void beforeFavouritesForm(Form f) {
        List list = this.findFavouritesList(f);
        Container selectedCell = createContainer(fetchResourceFile(), "VideoCellRenderer");
        
        Container unselectedCell = createContainer(fetchResourceFile(), "VideoCellRenderer");
        
        list.setRenderer(new GenericListCellRenderer(selectedCell, unselectedCell));
    }

    @Override
    protected void onFavouritesForm_FavouritesListAction(Component c, ActionEvent event) {
        onMain_VideoListAction(c, event);
        
    }
}
