package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@Beta
public final class Resources {

    /* renamed from: com.google.common.io.Resources.1 */
    static class C06751 implements InputSupplier<InputStream> {
        final /* synthetic */ URL val$url;

        C06751(URL url) {
            this.val$url = url;
        }

        public InputStream getInput() throws IOException {
            return this.val$url.openStream();
        }
    }

    private Resources() {
    }

    public static InputSupplier<InputStream> newInputStreamSupplier(URL url) {
        Preconditions.checkNotNull(url);
        return new C06751(url);
    }

    public static InputSupplier<InputStreamReader> newReaderSupplier(URL url, Charset charset) {
        return CharStreams.newReaderSupplier(newInputStreamSupplier(url), charset);
    }

    public static byte[] toByteArray(URL url) throws IOException {
        return ByteStreams.toByteArray(newInputStreamSupplier(url));
    }

    public static String toString(URL url, Charset charset) throws IOException {
        return CharStreams.toString(newReaderSupplier(url, charset));
    }

    public static <T> T readLines(URL url, Charset charset, LineProcessor<T> callback) throws IOException {
        return CharStreams.readLines(newReaderSupplier(url, charset), callback);
    }

    public static List<String> readLines(URL url, Charset charset) throws IOException {
        return CharStreams.readLines(newReaderSupplier(url, charset));
    }

    public static void copy(URL from, OutputStream to) throws IOException {
        ByteStreams.copy(newInputStreamSupplier(from), to);
    }

    public static URL getResource(String resourceName) {
        boolean z;
        URL url = Resources.class.getClassLoader().getResource(resourceName);
        if (url != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "resource %s not found.", resourceName);
        return url;
    }

    public static URL getResource(Class<?> contextClass, String resourceName) {
        boolean z;
        URL url = contextClass.getResource(resourceName);
        if (url != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "resource %s relative to %s not found.", resourceName, contextClass.getName());
        return url;
    }
}
