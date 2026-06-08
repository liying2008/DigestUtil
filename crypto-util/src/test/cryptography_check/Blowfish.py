from cryptography.hazmat.decrepit.ciphers.algorithms import Blowfish
from cryptography.hazmat.decrepit.ciphers.modes import CFB, OFB
from cryptography.hazmat.primitives import padding
from cryptography.hazmat.primitives.ciphers import Cipher, modes

from util import pad_right, to_base64

KEY = '1234567'
PLAIN_TEXT = '中文abc123'
plain_data = bytes(PLAIN_TEXT, 'utf-8')
IV = b'n834nbf#'


def blowfish_ecb():
    key = bytes(pad_right(KEY, 56, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(64).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(Blowfish(key), modes.ECB())
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [Blowfish/ECB]: gmtRaPCEs3O08w1qrvolYA==
    print(f'[Blowfish/ECB]: {to_base64(ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(64).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


def blowfish_cbc():
    key = bytes(pad_right(KEY, 56, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(64).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(Blowfish(key), modes.CBC(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [Blowfish/CBC]: bjgzNG5iZiNgA9FsCQP7+aqfsjdWRu89
    print(f'[Blowfish/CBC]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(64).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


def blowfish_cfb():
    key = bytes(pad_right(KEY, 56, '0'), 'utf-8')
    # CFB == CFB64
    cipher = Cipher(Blowfish(key), CFB(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [Blowfish/CFB]: bjgzNG5iZiNx0dKAVxkbHIvUB6Y=
    print(f'[Blowfish/CFB]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


def blowfish_ofb():
    key = bytes(pad_right(KEY, 56, '0'), 'utf-8')
    # OFB == OFB64
    cipher = Cipher(Blowfish(key), OFB(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [Blowfish/OFB]: bjgzNG5iZiNx0dKAVxkbHHIp2w8=
    print(f'[Blowfish/OFB]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


if __name__ == '__main__':
    blowfish_ecb()
    blowfish_cbc()
    blowfish_cfb()
    blowfish_ofb()
    # blowfish_ctr() # cipher Blowfish in CTR mode is not supported
