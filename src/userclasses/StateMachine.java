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
import com.codename1.ui.Display;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {
    
    private java.util.List<Video> videoList;
    private Video selectedVideo;
    private static String feedURL = "https://gdata.youtube.com/feeds/api/videos?q=xataface&max-results=30&v=2&alt=jsonc&orderby=published";
    
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
    }
    
    
    
    
    private java.util.List<Video> fetchVideoList(String url){
        final ArrayList<Video> out = new ArrayList<Video>();
        ConnectionRequest req = new ConnectionRequest(){

            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser p = new JSONParser();
                Map data = p.parseJSON(new InputStreamReader(input));
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
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return out;
        
    }
    
    


    @Override
    protected boolean initListModelVideoList(List cmp) {
        final java.util.List<Video> vids = new ArrayList<Video>();
        if ( videoList.isEmpty()){
            Display.getInstance().invokeAndBlock(new Runnable(){

                public void run() {
                    
                    videoList.addAll(fetchVideoList(feedURL));
                }
                
            });
        }
        vids.addAll(videoList);
        
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
            
            //Log.p(item+"");
            data[i++] = item;
            
        }
        
        cmp.setModel(new com.codename1.ui.list.DefaultListModel(data));
        
        return true;
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
        List l = (List)c;
        selectedVideo = (Video)((Map)l.getSelectedItem()).get("video");
        showForm("VideoPlayer", null);
        
    
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
}
