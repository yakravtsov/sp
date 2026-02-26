FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    unzip \
    git \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /tools

RUN wget https://github.com/iBotPeaches/Apktool/releases/download/v2.9.3/apktool_2.9.3.jar -O apktool.jar && \
    echo '#!/bin/bash\njava -jar /tools/apktool.jar "$@"' > /usr/local/bin/apktool && \
    chmod +x /usr/local/bin/apktool

RUN wget https://github.com/skylot/jadx/releases/download/v1.5.0/jadx-1.5.0.zip && \
    unzip jadx-1.5.0.zip -d /tools/jadx && \
    rm jadx-1.5.0.zip && \
    ln -s /tools/jadx/bin/jadx /usr/local/bin/jadx && \
    ln -s /tools/jadx/bin/jadx-gui /usr/local/bin/jadx-gui && \
    chmod +x /tools/jadx/bin/*

WORKDIR /workspace

CMD ["/bin/bash"]
