package stevendrake.bakingrecipes.Player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoPlayer {

    Context playerContext;
    SimpleExoPlayer player;
    SimpleExoPlayerView playerView;

    public VideoPlayer(Context context, SimpleExoPlayerView simpleExoPlayerView){

        playerContext = context;
        playerView = simpleExoPlayerView;
    }

    public void setExoPlayer(String mediaUrl){

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(playerContext),
                new DefaultTrackSelector(),
                new DefaultLoadControl()
        );
        playerView.setPlayer(player);

        player.prepare(getSource(mediaUrl, playerContext));
        player.setPlayWhenReady(true);
    }

    MediaSource getSource(String sourceUri, Context context){

        Uri parsedUri = Uri.parse(sourceUri);
        MediaSource playerSource = new ExtractorMediaSource(parsedUri,
                new DefaultDataSourceFactory(context, "Baking Recipes"),
                new DefaultExtractorsFactory(), null, null);
        return playerSource;
    }

    public void stopPlayer(){
        if (player != null){
            player.stop();
            player.release();
        }
    }
}
