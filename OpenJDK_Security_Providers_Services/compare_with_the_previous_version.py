#!/usr/bin/env python3

import os


def read_file(file):
    """
    读取文件内容，返回列表，去掉 [] 之间的内容以及前后空格
    :param file: 文件路径
    :return: list
    """
    with open(file, 'r', encoding='utf-8') as f:
        lines = f.read().splitlines()
    if not lines:
        return []
    result = []
    for line in lines:
        if line.strip():
            r_bracket = line.rfind(']')
            if r_bracket > 0:
                result.append(line[r_bracket + 1:].strip())
    return result


def write_diff_marker(file, diff):
    """
    新增的行，在最前面标记一个 + 号
    :param file: 需要添加标记的文件
    :param diff: 和前一个版本相比，有差异的行，可能是新增，也可能是删除
    :return: 在原文件上修改
    """
    print(f'{file}: {diff}')
    if not diff:
        return
    with open(file, 'r+', encoding='utf-8') as f:
        lines = f.read().splitlines()
        for i in range(len(lines)):
            if lines[i].strip():
                r_bracket = lines[i].rfind(']')
                if r_bracket > 0:
                    item = lines[i][r_bracket + 1:].strip()
                    if item in diff:
                        lines[i] = '+' + lines[i][1:]
        f.seek(0)
        f.write('\n'.join(lines))


def compare_and_write(_type):
    """
    和前一个版本比较，新增的标记一个 +
    :param _type: Cipher / MessageDigest
    """
    version_order = ['1.8', '11', '17', '21', '25']
    dir_path = os.path.join(CURRENT_PATH, _type)
    prev_list = []
    for version in version_order:
        file = os.path.join(dir_path, f'{_type}-{version}.txt')
        if not prev_list:
            prev_list = read_file(file)
            continue
        new_list = read_file(file)
        diff = set(prev_list) ^ set(new_list)
        write_diff_marker(file, diff)
        prev_list = new_list


def main():
    compare_and_write('Cipher')
    compare_and_write('MessageDigest')


if __name__ == '__main__':
    CURRENT_PATH = os.path.dirname(os.path.abspath(__file__))
    main()
