import argparse
import os.path
import ast
import pymysql
from openai import OpenAI
import json
client = OpenAI(
    # defaults to os.environ.get("OPENAI_API_KEY")
    api_key="sk-C2UV1lVrOQ7oEDwA693MqbmHaF5Ca3EWxZMiGZNGMmws4PFe",
    base_url="https://api.chatanywhere.tech/v1"
)

if pymysql.__version__ >= '1.0.0':
    from pymysql.converters import escape_string
else:
    escape_string = lambda x: pymysql.escape_string(x)

# 非流式响应
def gpt_35_api(messages: list):
    """为提供的对话消息创建新的回答

    Args:
        messages (list): 完整的对话消息
    """
    completion = client.chat.completions.create(model="gpt-3.5-turbo", messages=messages)
    return completion.choices[0].message.content

def gpt_35_api_stream(messages: list):
    """为提供的对话消息创建新的回答 (流式传输)

    Args:
        messages (list): 完整的对话消息
    """
    stream = client.chat.completions.create(
        model='gpt-3.5-turbo',
        messages=messages,
        stream=True,
    )
    for chunk in stream:
        if chunk.choices[0].delta.content is not None:
            print(chunk.choices[0].delta.content, end="")

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--id', type=int, default=-1,
                        help='id')
    parser.add_argument('--user', type=int, default=-1)
    parser.add_argument('--content', type=str, default="你好")
    FLAGS, unparsed = parser.parse_known_args()
    conn = pymysql.connect(
        host='120.78.7.40',
        port=3306,
        user='hailong',
        password='sustechcs304',
        database='cs304')
    cur = conn.cursor()
    if FLAGS.id == -1:
        messages = [{"role": "user", "content": FLAGS.content}]
    else:
        sql = f"select * from ai where id = {FLAGS.id}"
        cur.execute(sql)
        result = cur.fetchone()
        messages = list(json.loads(result[0]))
        messages.append({"role": "user", "content": FLAGS.content})
    ai_message = gpt_35_api(messages)
    messages.append({"role": "assistant", "content": ai_message})
    json = json.dumps(messages, ensure_ascii=False)
    json = escape_string(json)
    if FLAGS.id == -1:
        sql = f"insert into ai (content, user) VALUES ('{json}', {FLAGS.user})"
    else:
        sql = f"update ai set content = '{json}' where id = {FLAGS.id}"

    cur.execute(sql)
    conn.commit()
    cur.close()
    conn.close()
    print(ai_message)

    # 非流式调用
    # gpt_35_api(messages)
    # 流式调用
