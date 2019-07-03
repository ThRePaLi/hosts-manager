package me.chenjp.hostsmanager.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class AnalysisHelper {

    public static final String SPLIT_RULE = " +";
    public static final int LINE_LEN = 2;
    @Value("{win.hosts.path}")
    private String winHostsPath;
    @Value("{linux.hosts.path}")
    private String linuxHostsPath;
    @Value("{mac.hosts.path}")
    private String macHostsPath;

    public List<String> getDataFromFile(String file, Charset charset) {

        List<String> res = new LinkedList<>();
        try(FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, charset);
            BufferedReader br = new BufferedReader(isr)){

            String line = null;
            while ((line=br.readLine()) != null){ res.add(line); }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }

    public Map<String, String> annlysisHosts(Collection<String> lines){
        Map<String, String> result = new HashMap<>();
        if (lines==null || lines.isEmpty()) { return result; }

        lines.stream()
                .filter(str -> str.split(SPLIT_RULE).length == LINE_LEN)
                .map(e -> e.split(SPLIT_RULE))
                .forEach(strs -> result.put(strs[0], strs[1]));
        return result;
    }

    public static void main(String[] args) {
        String path = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        List<String> dataFromFile = new AnalysisHelper().getDataFromFile(path, StandardCharsets.UTF_8);

        Consumer<String> consumer = data -> {
            String[] strs = data.split(SPLIT_RULE);
            System.out.println(strs[0] + strs[1]);
        };
        dataFromFile.stream().forEach(consumer);
    }

}
