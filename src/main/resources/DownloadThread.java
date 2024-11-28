import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class DownloadThread  extends Thread {
    private String url;

    public void download() {
        String url = "https://www.yuque.com/tulingzhouyu/sfx8p0/qnxql079alg2ghhz/markdown?attachment=true&latexcode=false&anchor=false&linebreak=false";  //要下载的文件的URL地址
        String path =  getClass().getResource("/").getPath()+ UUID.randomUUID().toString() +".md";  //要保存的文件路径

        HttpResponse response = HttpUtil.createGet(url).execute();  //使用Hutool的HttpUtil发送GET请求获取文件内容
        FileUtil.writeBytes(response.bodyBytes(), path);  //将文件内容写入指定的文件路径
    }

    @Override
    public void run() {
        System.out.println("开始下载" );
        // 执行下载任务
        download();
        // 使用yield方法让出CPU执行权
        // Thread.yield();

        System.out.println("下载完成" );
    }
}