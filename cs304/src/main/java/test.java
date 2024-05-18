import com.example.cs304.common.Util;
import com.example.cs304.entity.CourseForTimetable;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import com.unfbx.chatgpt.interceptor.OpenAiResponseInterceptor;
import com.unfbx.chatgpt.sse.ConsoleEventSourceListener;
import com.unfbx.chatgpt.utils.TikTokensUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class test {
    private static OpenAiClient v2;
        public static void main(String[] args) {
                OpenAiStreamClient openAiClient = OpenAiStreamClient.builder()
                        .apiKey(Arrays.asList("sk-6BfW5OzsVWwTmpnqqx8ORTPFSIc7ftcIqRp2OwgkOJthG6pj"))
                        //自定义key的获取策略：默认KeyRandomStrategy
                        .keyStrategy(new KeyRandomStrategy())
//                            .keyStrategy(new FirstKeyStrategy())
                        //自己做了代理就传代理地址，没有可不不传
                        .apiHost("https://api.chatanywhere.com.cn/")
                        .build();
                //聊天模型：gpt-3.5
                Message message = Message.builder().role(Message.Role.USER).content("你好啊我的伙伴！").build();
                ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
            ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener();
                openAiClient.streamChatCompletion(chatCompletion,eventSourceListener);


        }
}
