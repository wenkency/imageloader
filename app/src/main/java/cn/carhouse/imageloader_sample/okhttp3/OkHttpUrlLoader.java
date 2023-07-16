package cn.carhouse.imageloader_sample.okhttp3;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;

import okhttp3.OkHttpClient;

/** A simple model loader for fetching media over http/https using OkHttp. */
public class OkHttpUrlLoader implements ModelLoader<OkGlideUrl, InputStream> {

  private final OkHttpClient client;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public OkHttpUrlLoader(@NonNull OkHttpClient client) {
    this.client = client;
  }

  @Override
  public boolean handles(@NonNull OkGlideUrl url) {
    return true;
  }

  @Override
  public LoadData<InputStream> buildLoadData(
      @NonNull OkGlideUrl model, int width, int height, @NonNull Options options) {
    return new LoadData<>(model, new OkHttpStreamFetcher(client, model));
  }

  /** The default factory for {@link OkHttpUrlLoader}s. */
  // Public API.
  @SuppressWarnings("WeakerAccess")
  public static class Factory implements ModelLoaderFactory<OkGlideUrl, InputStream> {
    private static volatile OkHttpClient internalClient;
    private final OkHttpClient client;

    private static OkHttpClient getInternalClient() {
      if (internalClient == null) {
        synchronized (Factory.class) {
          if (internalClient == null) {
            internalClient = new OkHttpClient.Builder().build();
          }
        }
      }
      return internalClient;
    }

    /** Constructor for a new Factory that runs requests using a static singleton client. */
    public Factory() {
      this(getInternalClient());
    }

    /**
     * Constructor for a new Factory that runs requests using given client.
     *
     * @param client this is typically an instance of {@code OkHttpClient}.
     */
    public Factory(@NonNull OkHttpClient client) {
      this.client = client;
    }

    @NonNull
    @Override
    public ModelLoader<OkGlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new OkHttpUrlLoader(client);
    }

    @Override
    public void teardown() {
      // Do nothing, this instance doesn't own the client.
    }
  }
}
