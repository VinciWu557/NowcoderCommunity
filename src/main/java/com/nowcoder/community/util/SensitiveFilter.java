package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/11/29 - 11 - 29 - 11:41
 * @Description: com.nowcoder.community.util
 * @version: 1.0
 */
@Component
public class SensitiveFilter {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    /**
     * 替换符
     */
    private static final String REPLACEMENT = "***";

    /**
     * 根节点
     */
    private TrieNode rootNode = new TrieNode();

    /**
     * 这个 Bean即SensitiveFilter 在程序启动时就实例化,
     * 然后调用构造器,
     * 之后这个方法就会被调用.
     * this.getClass().getClassLoader() : 类加载器是从类路径下加载资源, 也就是 target/classes
     * 在 getResourceAsStream("sensitive-words.txt") 直接写文件名即可
     */
    @PostConstruct
    public void init() {
        try(
                // 初始化输入流并转换为缓冲流
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                // 此处体现了装饰器模式
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                // 将一个敏感词添加到前缀树中
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败: " + e.getMessage());
        }
    }

    /**
     * 将一个敏感词添加到前缀树中
     *
     * @param keyword 敏感词
     */
    private void addKeyword(String keyword) {
        TrieNode tempNode = rootNode; // tempNode指向根节点, 相当于指针
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            // 如果根节点没有子节点, 则初始化并添加
            if (subNode == null) {
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }

            // 指向子节点, 进入下一轮
            tempNode = subNode;

            // 设置结束标识
            if (i == keyword.length()-1) {
                tempNode.setKeywordEnd(true);
            }
        }

    }

    /**
     * 过滤敏感词
     * 对于敏感词中有符号的先去除符号
     * 需要三个指针
     * 还需要跳过特殊符号
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        // 先判断是否为空
        if (StringUtils.isBlank(text)) {
            return null;
        }

        // 指针1
        TrieNode tempNode = rootNode;
        // 指针2
        int begin = 0;
        // 指针3
        int position = 0;
        // 结果
        StringBuilder sb = new StringBuilder();

        // 检测: 默认处于根节点, 所以检测它的下一级
        while (position < text.length()) {
            char c = text.charAt(position);

            // 跳过符号: 指针向下走一步
            if (isSymbol(c)) {
                // 若指针1处于根节点，将此符号计入结果，让指针2向下走一步
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                // 无论符号在开头或者中间，指针3都向下走一步
                position++;
                continue;
            }

            // 若不是符号: 检查下级节点:
            tempNode = tempNode.getSubNode(c); // 指针指向下级节点
            if (tempNode == null) { // 下级没有结点
                // 以begin开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                // 进入下一个位置
                position = ++begin;
                // 归位: 重新指向根节点
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // 发现敏感词，将begin~position字符串替换掉
                sb.append(REPLACEMENT);
                // 进入下一个位置
                begin = ++position;
                // 重新指向根节点
                tempNode = rootNode;
            } else {
                // 检查下一个字符
                position++;
            }
        } // while 结束

        // 将最后一批字符计入结果: 指针3 提前到终点, 但 指针2 并未到终点.
        sb.append(text.substring(begin));

        return sb.toString();
    }

    /**
     * 判断是否是符号
     * CharUtils.isAsciiAlphanumeric 方法可以判断是否是 合法字符,
     * 取反表示 它不是合法字符, 即它是特殊符号
     * 0x2E80 到 0x9FFF为东亚文字, 不认为是符号
     */
    private boolean isSymbol(char c) {
        // 既不是字母数字也不是东亚文字 (0x2E80 到 0x9FFF为东亚文字), 可以认为是符号
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    /**
     * 定义前缀树节点
     */
    private class TrieNode {

        // 关键词结束标志
        private boolean isKeywordEnd = false;

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        // 子节点 (key是下级字符, value是下级节点)
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        /**
         * 添加子节点
         *
         * @param c    字符
         * @param node 前缀树节点
         */
        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        /**
         * 获取子节点
         *
         * @param c 字符
         * @return 子节点的引用
         */
        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }

    }
}
