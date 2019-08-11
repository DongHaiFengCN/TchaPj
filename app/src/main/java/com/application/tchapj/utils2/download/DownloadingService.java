package com.application.tchapj.utils2.download;

/**
 * @author cenxiaozhong
 * @date 2018/2/4
 */
public interface DownloadingService {
    /**
     * 当前任务是否已经终止
     * @return
     */
    boolean isShutdown();

    /**
     * 终止当前下载的任务
     * @return ExtraService#performReDownload 重新提交下载任务
     */
    AgentWebDownloader.ExtraService shutdownNow();

}
