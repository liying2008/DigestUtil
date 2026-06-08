from cryptography.hazmat.decrepit.ciphers.algorithms import RC2
from cryptography.hazmat.primitives import padding
from cryptography.hazmat.primitives.ciphers import Cipher, modes

from util import pad_right, to_base64

KEY = '1234567'
PLAIN_TEXT = '中文abc123'
plain_data = bytes(PLAIN_TEXT, 'utf-8')
IV = b'n834nbf#'


def rc2_cbc():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(64).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(RC2(key), modes.CBC(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [RC2/CBC]: bjgzNG5iZiNgA9FsCQP7+aqfsjdWRu89
    print(f'[RC2/CBC]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(64).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


if __name__ == '__main__':
    # rc2_ecb() # cipher RC2 in ECB mode is not supported
    rc2_cbc()
    # rc2_cfb() # cipher RC2 in CFB mode is not supported
    # rc2_ofb() # cipher RC2 in OFB mode is not supported
    # rc2_ctr() # cipher RC2 in CTR mode is not supported
