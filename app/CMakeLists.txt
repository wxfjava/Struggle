# cmake最小支持版本
cmake_minimum_required(VERSION 3.4.1)

# 创建一个全局变量
file(GLOB source src/main/cpp/*.c src/main/cpp/*.cpp)
add_library(
        # 库名称
        fh

        # 库类型 SHARED：动态库，STATIC：静态库
        SHARED

        # 源文件
        ${source})

# 引入头文件的目录
include_directories(src/main/cpp/include/)

find_library(
        log-lib
        log)

# 链接 fh：目标库 后面的是需要依赖的库
target_link_libraries(
        fh
        ${log-lib})