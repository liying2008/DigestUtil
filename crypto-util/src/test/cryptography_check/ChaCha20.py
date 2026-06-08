import struct

from cryptography.hazmat.primitives.ciphers import Cipher
from cryptography.hazmat.primitives.ciphers.aead import ChaCha20Poly1305
from cryptography.hazmat.primitives.ciphers.algorithms import ChaCha20

from util import pad_right, to_base64

KEY = '1234567'
PLAIN_TEXT = '中文abc123'
plain_data = bytes(PLAIN_TEXT, 'utf-8')
IV = b'n834nbf#0ewfv4d0'


def chacha20():
    key = bytes(pad_right(KEY, 32, '0'), 'utf-8')
    # 12 字节的 nonce
    nonce = IV[0:12]
    # initial counter
    counter = 1

    nonce16 = (
        struct.pack("<I", counter)  # 加上 4 字节的 counter，组成 16 字节的 nonce
        + nonce
    )
    cipher = Cipher(ChaCha20(key, nonce16), None)
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [ChaCha20]: bjgzNG5iZiMwZXdm/OGh2Jcep2ldH5Km
    print(f'[ChaCha20]: {to_base64(nonce + ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


def chacha20_poly1305():
    key = bytes(pad_right(KEY, 32, '0'), 'utf-8')
    # 12 字节的 nonce
    nonce = IV[0:12]
    cipher = ChaCha20Poly1305(key)
    ct1 = cipher.encrypt(nonce, plain_data, None)
    # [ChaCha20Poly1305]: bjgzNG5iZiMwZXdm/OGh2Jcep2ldH5Kmwgpul6gIIi3JOvPvKlDNag==
    print(f'[ChaCha20Poly1305]: {to_base64(nonce + ct1)}')
    pt1 = cipher.decrypt(nonce, ct1, None)
    assert pt1 == plain_data

    aad = b'teststring'
    ct1 = cipher.encrypt(nonce, plain_data, aad)
    # [ChaCha20Poly1305] with AAD: bjgzNG5iZiMwZXdm/OGh2Jcep2ldH5KmVcpKz5DhkCNeBeSoMB+FWQ==
    print(f'[ChaCha20Poly1305] with AAD: {to_base64(nonce + ct1)}')
    pt1 = cipher.decrypt(nonce, ct1, aad)
    assert pt1 == plain_data


if __name__ == '__main__':
    chacha20()
    chacha20_poly1305()
