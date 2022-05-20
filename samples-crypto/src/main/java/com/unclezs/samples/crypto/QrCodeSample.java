package com.unclezs.samples.crypto;

import cn.hutool.extra.qrcode.QrCodeUtil;

import java.io.File;

/**
 * @author blog.unclezs.com
 * @since 2022/5/19 17:29
 */
public class QrCodeSample {
  public static void main(String[] args) {
    QrCodeUtil.generate(
        "S5Q2vAWhgAIrhD/YL4znGzjgOIrapmhZcBb355O87Vo03MgAaaDBey25wfr79U/KjCNovqEM55jCE2EAC52"
            + "oTYxMg4DWQsX3rjZeDjEpV9PXkCi+WBV6r3OH3CouJrgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA38NDXePm"
            + "fyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA3"
            + "8NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbmLk71trv6dl8PO/I92oI"
            + "WlRB7MXeA38NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbmLk71trv6dl"
            + "8PO/I92oIWlRB7MXeA38NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbm"
            + "Lk71trv6dl8PO/I92oIWlRB7MXeA38NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ"
            + "==\"rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA38NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0"
            + "jawimR9dNkiQ==\"rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA38NDXePmfyUvMExExtWixuo7G69Qj+O"
            + "TkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA38NDXePmfyUvMExExtWi"
            + "xuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA38NDXePmfy"
            + "UvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA"
            + "38NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==\"ePMU0jGs70J8aA6dLkDN1W/Z"
            + "LcdLfHgkMCvRLSibMu"
            + "Wq9B63nQL7wcZvK1BikAIEMPqobpM2uC+EgEzhA4xjh0x1ZmiZT8mrCDwCal2qRly6PVp9OV35O7hfW+E9Dbst"
            + "rgqVQbmLk71trv6dl8PO/I92oIWlRB7MXeA38NDXePmfyUvMExExtWixuo7G69Qj+OTkGvzUUWGJ0jawimR9dNkiQ==",
        600, 600, new File("/Users/unclezs/tmp/qrcode.jpg"));
  }
}
