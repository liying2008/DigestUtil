from cryptography.hazmat.decrepit.ciphers.modes import CFB, CFB8, OFB
from cryptography.hazmat.primitives import padding
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives.ciphers.aead import AESGCM
from cryptography.hazmat.primitives.ciphers.modes import CTR

from util import pad_right, to_base64

KEY = '12345678'
PLAIN_TEXT = '中文abc123'
plain_data = bytes(PLAIN_TEXT, 'utf-8')
IV = b'n834nbf#0ewfv4d0'


def aes_ecb():
    key = bytes(pad_right(KEY, 32, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(128).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(algorithms.AES(key), modes.ECB())
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [AES/ECB]: XwkL3eJJiio3KqMdqqWmsQ==
    print(f'[AES/ECB]: {to_base64(ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(128).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


def aes_cbc():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    # PKCS7 Padding ，对应 Java 中的 PKCS5Padding
    padder = padding.PKCS7(128).padder()
    padded_data = padder.update(plain_data) + padder.finalize()
    cipher = Cipher(algorithms.AES(key), modes.CBC(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(padded_data) + encryptor.finalize()
    # [AES/CBC]: bjgzNG5iZiMwZXdmdjRkMP3s6cfIadAjqIepuqq76OQ=
    print(f'[AES/CBC]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    padded_plaintext = decryptor.update(ct) + decryptor.finalize()
    # 去除 PKCS7 Padding
    unpadder = padding.PKCS7(128).unpadder()
    pt = unpadder.update(padded_plaintext) + unpadder.finalize()
    assert pt == plain_data


def aes_cfb():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    # CFB == CFB128
    cipher = Cipher(algorithms.AES(key), CFB(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [AES/CFB]: bjgzNG5iZiMwZXdmdjRkMNEFUgHuJwq0tToXTA==
    print(f'[AES/CFB]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data

    # CFB8
    cipher = Cipher(algorithms.AES(key), CFB8(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [AES/CFB8]: bjgzNG5iZiMwZXdmdjRkMNHgK/MsX5eu1UCb8g==
    print(f'[AES/CFB8]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


def aes_ofb():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    # OFB == OFB128
    cipher = Cipher(algorithms.AES(key), OFB(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [AES/OFB]: bjgzNG5iZiMwZXdmdjRkMNEFUgHuJwq0tToXTA==
    print(f'[AES/OFB]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


def aes_ctr():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    cipher = Cipher(algorithms.AES(key), CTR(IV))
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [AES/CTR]: bjgzNG5iZiMwZXdmdjRkMNEFUgHuJwq0tToXTA==
    print(f'[AES/CTR]: {to_base64(IV + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


def aes_gcm():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    aesgcm = AESGCM(key)
    nonce = IV[0:12]
    ct1 = aesgcm.encrypt(nonce, plain_data, None)
    # [AES/GCM]: bjgzNG5iZiMwZXdm1zWVZx04KMkn0NHXIWbzsGEOoR5Tfv6bUd7Zdg==
    print(f'[AES/GCM]: {to_base64(nonce + ct1)}')
    pt1 = aesgcm.decrypt(nonce, ct1, None)
    assert pt1 == plain_data

    aad = b'teststring'
    ct1 = aesgcm.encrypt(nonce, plain_data, aad)
    # [AES/GCM] with AAD: bjgzNG5iZiMwZXdm1zWVZx04KMkn0NHX0Q+e8u8HxRTfy7JuVhLKdw==
    print(f'[AES/GCM] with AAD: {to_base64(nonce + ct1)}')
    pt1 = aesgcm.decrypt(nonce, ct1, aad)
    assert pt1 == plain_data


if __name__ == '__main__':
    aes_ecb()
    aes_cbc()
    aes_cfb()
    aes_ofb()
    aes_ctr()
    aes_gcm()
