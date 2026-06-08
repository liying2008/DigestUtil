from cryptography.hazmat.decrepit.ciphers.algorithms import TripleDES
from cryptography.hazmat.decrepit.ciphers.modes import CFB, CFB8, OFB
from cryptography.hazmat.primitives import padding
from cryptography.hazmat.primitives.ciphers import Cipher, modes

from util import pad_right, to_base64

KEY = '12345678'
PLAIN_TEXT = '中文abc123'
plain_data = bytes(PLAIN_TEXT, 'utf-8')
IV = b'n834nbf#'


def tripledes_ecb():
    key = bytes(pad_right(KEY, 24, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(64).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(TripleDES(key), modes.ECB())
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [TripleDES/ECB]: gmtRaPCEs3O08w1qrvolYA==
    print(f'[TripleDES/ECB]: {to_base64(ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(64).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


def tripledes_cbc():
    key = bytes(pad_right(KEY, 24, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(64).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(TripleDES(key), modes.CBC(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [TripleDES/CBC]: bjgzNG5iZiNgA9FsCQP7+aqfsjdWRu89
    print(f'[TripleDES/CBC]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(64).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


def tripledes_cfb():
    key = bytes(pad_right(KEY, 24, '0'), 'utf-8')
    # CFB == CFB64
    cipher = Cipher(TripleDES(key), CFB(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [TripleDES/CFB]: bjgzNG5iZiNx0dKAVxkbHIvUB6Y=
    print(f'[TripleDES/CFB]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data

    # CFB8
    cipher = Cipher(TripleDES(key), CFB8(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [TripleDES/CFB8]: bjgzNG5iZiNxWvT/wDQzDotwW7U=
    print(f'[TripleDES/CFB8]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


def tripledes_ofb():
    key = bytes(pad_right(KEY, 24, '0'), 'utf-8')
    # OFB == OFB64
    cipher = Cipher(TripleDES(key), OFB(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [TripleDES/OFB]: bjgzNG5iZiNx0dKAVxkbHHIp2w8=
    print(f'[TripleDES/OFB]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


if __name__ == '__main__':
    tripledes_ecb()
    tripledes_cbc()
    tripledes_cfb()
    tripledes_ofb()
    # tripledes_ctr() # cipher 3DES in CTR mode is not supported
